package com.fifty.socialnetwork.featurechat.presentation.message

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifty.socialnetwork.core.domain.states.StandardTextFieldState
import com.fifty.socialnetwork.core.presentation.PagingState
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.util.DefaultPaginator
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featurechat.domain.model.Message
import com.fifty.socialnetwork.featurechat.domain.usecase.ChatUseCases
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val chatUseCases: ChatUseCases,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _messageTextFieldState = mutableStateOf(StandardTextFieldState())
    val messageTextFieldState: State<StandardTextFieldState> = _messageTextFieldState

    private val _pagingState = mutableStateOf<PagingState<Message>>(PagingState())
    val pagingState: State<PagingState<Message>> = _pagingState

    private val _state = mutableStateOf(MessageState())
    val state: State<MessageState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _messageUpdateEvent = MutableSharedFlow<MessageUpdateEvent>(replay = 1)
    val messageUpdateEvent = _messageUpdateEvent.asSharedFlow()

    private val paginator = DefaultPaginator(
        onLoadUpdated = { isLoading ->
            _pagingState.value = pagingState.value.copy(isLoading = isLoading)
        },
        onRequest = { nextPage ->
            savedStateHandle.get<String>("chatId")?.let { chatId ->
                chatUseCases.getMessagesForChat(
                    chatId, nextPage
                )
            } ?: Resource.Error(UiText.unknownError())
        },
        onError = { errorUiText ->
            _eventFlow.emit(UiEvent.ShowSnackBar(errorUiText))
        },
        onSuccess = { messages ->
            _pagingState.value = pagingState.value.copy(
                items = pagingState.value.items + messages,
                endReached = messages.isEmpty(),
                isLoading = false
            )
            viewModelScope.launch {
                _messageUpdateEvent.emit(MessageUpdateEvent.MessagePageLoaded)
            }
        }
    )

    init {
        chatUseCases.initializeRepository()
        loadNextMessages()
        observeChatEvents()
        observeChatMessages()
    }

    private fun observeChatMessages() {
        viewModelScope.launch {
            chatUseCases.observeMessages()
                .collect { message ->
                    _pagingState.value = pagingState.value.copy(
                        items = pagingState.value.items + message
                    )
                    _messageUpdateEvent.emit(MessageUpdateEvent.SingleMessageReceived)
                }
        }
    }

    private fun observeChatEvents() {
        chatUseCases.observeChatEvents()
            .onEach { event ->
                when (event) {
                    is WebSocket.Event.OnConnectionOpened<*> -> {
                        println("Connection was opened")
                    }

                    is WebSocket.Event.OnConnectionFailed -> {
                        println("Connection failed: ${event.throwable}")
                    }

                    else -> Unit
                }
            }.launchIn(viewModelScope)
    }

    fun loadNextMessages() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private fun sendMessage() {
        val toId = savedStateHandle.get<String>("remoteUserId") ?: return
        if (messageTextFieldState.value.text.isBlank()) {
            return
        }
        val chatId = savedStateHandle.get<String>("chatId")
        chatUseCases.sendMessage(toId, messageTextFieldState.value.text, chatId)
        _messageTextFieldState.value = StandardTextFieldState(text = "")
        _state.value = state.value.copy(
            canSendMessage = false
        )
    }

    fun onEvent(event: MessageEvent) {
        when (event) {
            is MessageEvent.EnteredMessage -> {
                _messageTextFieldState.value = messageTextFieldState.value.copy(
                    text = event.message
                )
                _state.value = state.value.copy(
                    canSendMessage = event.message.isNotBlank()
                )
            }

            is MessageEvent.SendMessage -> {
                sendMessage()
            }
        }
    }

    sealed class MessageUpdateEvent {
        object SingleMessageReceived : MessageUpdateEvent()
        object MessagePageLoaded : MessageUpdateEvent()
        object MessageSent : MessageUpdateEvent()
    }
}