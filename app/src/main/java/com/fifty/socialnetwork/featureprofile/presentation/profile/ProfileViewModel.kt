package com.fifty.socialnetwork.featureprofile.presentation.profile

import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.domain.usecase.GetOwnUserIdUseCase
import com.fifty.socialnetwork.core.presentation.PagingState
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.util.Event
import com.fifty.socialnetwork.core.util.ParentType
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featurepost.domain.usecase.PostUseCases
import com.fifty.socialnetwork.featurepost.presentation.personlist.PostEvent
import com.fifty.socialnetwork.featureprofile.domain.usecase.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    private val postUseCases: PostUseCases,
    private val getOwnUserId: GetOwnUserIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _toolbarState = mutableStateOf(ProfileToolbarState())
    val toolbarState: State<ProfileToolbarState> = _toolbarState

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var page = 0
    private val _pagingState = mutableStateOf(PagingState<Post>())
    val pagingState: State<PagingState<Post>> = _pagingState

    fun setExpandedRatio(ratio: Float) {
        _toolbarState.value = _toolbarState.value.copy(expandedRatio = ratio)
    }

    fun setToolbarOffsetY(value: Float) {
        _toolbarState.value = _toolbarState.value.copy(toolbarOffsetY = value)
    }

    init {
        loadNextPosts()
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetProfile -> {

            }

            is ProfileEvent.LikePost -> {
                viewModelScope.launch {
                    toggleLikeForParent(
                        event.postId,
                        isLiked = false
                    )
                }
            }
        }
    }

    fun loadNextPosts() {
        viewModelScope.launch {
            _pagingState.value = pagingState.value.copy(
                isLoading = true
            )
            val userId = savedStateHandle.get<String>("userId") ?: getOwnUserId()
            val result = profileUseCases.getPostsForProfile(
                userId = userId,
                page = page
            )
            when (result) {
                is Resource.Success -> {
                    val posts = result.data ?: emptyList()
                    _pagingState.value = pagingState.value.copy(
                        items = pagingState.value.items + posts,
                        endReached = posts.isEmpty(),
                        isLoading = false
                    )
                    page++
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(result.uiText ?: UiText.unknownError())
                    )
                    _pagingState.value = pagingState.value.copy(
                        isLoading = false
                    )
                }
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

    fun getProfile(userId: String?) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = profileUseCases.getProfile(
                userId ?: getOwnUserId()
            )
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        profile = result.data,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UiEvent.ShowSnackBar(
                            uiText = result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }
}