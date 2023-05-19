package com.fifty.socialnetwork.core.presentation.util

import com.fifty.socialnetwork.core.util.Event
import com.fifty.socialnetwork.core.util.UiText

sealed class UiEvent : Event() {
    data class ShowSnackBar(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    object OnLogin : UiEvent()
}