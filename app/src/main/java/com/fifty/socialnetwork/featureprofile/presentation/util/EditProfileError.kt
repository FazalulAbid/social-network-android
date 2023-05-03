package com.fifty.socialnetwork.featureprofile.presentation.util

sealed class EditProfileError : Error() {
    object FieldEmpty : EditProfileError()
}