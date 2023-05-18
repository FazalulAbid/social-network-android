package com.fifty.socialnetwork.featurepost.presentation.personlist

import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.util.Event

sealed class PostEvent : Event() {
    object OnLiked : PostEvent()
}
