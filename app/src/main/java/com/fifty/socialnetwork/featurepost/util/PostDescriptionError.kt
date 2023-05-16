package com.fifty.socialnetwork.featurepost.util


sealed class PostDescriptionError : com.fifty.socialnetwork.core.util.Error() {
    object FieldEmpty : PostDescriptionError()
}
