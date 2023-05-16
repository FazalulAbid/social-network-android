package com.fifty.socialnetwork.featureprofile.presentation.search

sealed class SearchEvent {
    data class Query(val query: String) : SearchEvent()
    data class ToggleFollowState(val userId:String) : SearchEvent()
}