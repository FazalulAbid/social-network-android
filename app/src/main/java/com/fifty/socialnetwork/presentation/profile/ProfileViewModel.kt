package com.fifty.socialnetwork.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel() {
    private val _toolbarOffsetY = mutableStateOf(0f)
    val toolbarOffsetY: State<Float> = _toolbarOffsetY

    private val _expandedRatio = mutableStateOf(1f)
    val expandedRatio: State<Float> = _expandedRatio

    fun setToolbarOffsetY(value: Float) {
        _toolbarOffsetY.value = value
    }

    fun setExpandedRatio(ratio: Float) {
        _expandedRatio.value = ratio
    }
}