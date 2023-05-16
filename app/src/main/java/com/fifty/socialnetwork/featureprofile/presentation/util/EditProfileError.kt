package com.fifty.socialnetwork.featureprofile.presentation.util

sealed class EditProfileError : com.fifty.socialnetwork.core.util.Error() {
    object FieldEmpty : EditProfileError()
}