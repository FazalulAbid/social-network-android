package com.fifty.socialnetwork.core.presentation.util

import com.fifty.socialnetwork.core.util.UiText

sealed class UiEvent {
    data class SnackBarEvent(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
}