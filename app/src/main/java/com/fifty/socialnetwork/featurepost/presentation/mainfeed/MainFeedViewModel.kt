package com.fifty.socialnetwork.featurepost.presentation.mainfeed

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Event
import com.fifty.socialnetwork.core.util.ParentType
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurepost.data.paging.PostSource
import com.fifty.socialnetwork.featurepost.domain.usecase.PostUseCases
import com.fifty.socialnetwork.featurepost.presentation.personlist.PostEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val postUseCases: PostUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainFeedState())
    val state: State<MainFeedState> = _state

    val posts = postUseCases.getPostsForFollowsUseCase()
        .cachedIn(viewModelScope)

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: MainFeedEvent) {
        when (event) {
            is MainFeedEvent.LoadMorePosts -> {
                _state.value = state.value.copy(
                    isLoadingNewPost = true
                )
            }

            is MainFeedEvent.LoadedPage -> {
                _state.value = state.value.copy(
                    isLoadingFirstTime = false,
                    isLoadingNewPost = false
                )
            }

            is MainFeedEvent.LikedPost -> {

            }
        }
    }

    private fun toggleLikeForParent(
        parentId: String,
        isLiked: Boolean
    ) {
        viewModelScope.launch {
            val result = postUseCases.toggleLikeForParent(
                parentId = parentId,
                parentType = ParentType.Post.type,
                isLiked = isLiked
            )
            when (result) {
                is Resource.Success -> {
                    _eventFlow.emit(PostEvent.OnLiked)
                }

                is Resource.Error -> {
                }
            }
        }
    }
}