package com.fifty.socialnetwork.featurepost.util

sealed class PostDescriptionError : Error() {
    object FieldEmpty : PostDescriptionError()
}
