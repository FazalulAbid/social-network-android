package com.fifty.socialnetwork.featurechat.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featurechat.domain.usecase.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCases: ChatUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ChatState())
    val state: State<ChatState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadChats()
    }

    private fun loadChats() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = chatUseCases.getChatsForUser()
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        chats = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(
                            result.uiText ?: UiText.unknownError()
                        )
                    )
                    _state.value = state.value.copy(isLoading = false)
                }
            }
        }
    }
}