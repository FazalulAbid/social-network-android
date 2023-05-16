package com.fifty.socialnetwork.featureactivity.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.fifty.socialnetwork.featureactivity.domain.usecase.GetActivitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val getActivities: GetActivitiesUseCase
) : ViewModel() {

    val activities = getActivities().cachedIn(viewModelScope)

    private val _state = mutableStateOf(ActivityState())
    fun onEvent(event: ActivityEvent) {
        when (event) {
            is ActivityEvent.ClickedOnParent -> {

            }

            is ActivityEvent.ClickedOnUser -> {

            }

        }
    }

    val state: State<ActivityState> = _state
}