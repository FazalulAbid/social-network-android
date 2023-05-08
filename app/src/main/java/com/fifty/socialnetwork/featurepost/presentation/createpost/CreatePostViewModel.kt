package com.fifty.socialnetwork.featurepost.presentation.createpost

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifty.socialnetwork.core.domain.states.StandardTextFieldState
import com.fifty.socialnetwork.featurepost.domain.usecase.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val postUseCase: PostUseCases
) : ViewModel() {

    private val _descriptionState = mutableStateOf<StandardTextFieldState>(StandardTextFieldState())
    val descriptionState: State<StandardTextFieldState> = _descriptionState

    private val _chosenImageUri = mutableStateOf<Uri?>(null)
    val chosenImageUri: State<Uri?> = _chosenImageUri

    fun onEvent(event: CreatePostEvent) {
        when (event) {
            is CreatePostEvent.EnterDescription -> {
                _descriptionState.value = descriptionState.value.copy(
                    text = event.value
                )
            }
            is CreatePostEvent.PickImage -> {
                _chosenImageUri.value = event.uri
            }
            is CreatePostEvent.CropImage -> {
                _chosenImageUri.value = event.uri
            }
            CreatePostEvent.PostImage -> {
                chosenImageUri.value?.let { uri ->
                    viewModelScope.launch {
                        postUseCase.createPostUseCase(
                            description = descriptionState.value.text,
                            imageUri = uri
                        )
                    }
                }
            }
        }
    }
}