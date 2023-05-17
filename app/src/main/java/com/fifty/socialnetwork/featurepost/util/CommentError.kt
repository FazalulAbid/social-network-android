package com.fifty.socialnetwork.featurepost.util

import com.fifty.socialnetwork.core.util.Error

sealed class CommentError : Error() {
    object FieldEmpty : CommentError()
}
