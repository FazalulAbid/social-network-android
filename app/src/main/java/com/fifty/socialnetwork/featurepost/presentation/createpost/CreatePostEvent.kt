package com.fifty.socialnetwork.featurepost.presentation.createpost

import android.net.Uri

sealed class CreatePostEvent {
    data class EnterDescription(val value: String) : CreatePostEvent()
    data class PickImage(val uri: Uri?) : CreatePostEvent()
    object PostImage : CreatePostEvent()
}
