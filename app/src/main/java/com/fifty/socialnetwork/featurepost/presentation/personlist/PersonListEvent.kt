package com.fifty.socialnetwork.featurepost.presentation.personlist

sealed class PersonListEvent {
    data class ToggleFollowStateForUser(val userId:String): PersonListEvent()
}
