package com.fifty.socialnetwork.featurepost.presentation.mainfeed

import androidx.lifecycle.ViewModel
import com.fifty.socialnetwork.featurepost.domain.usecase.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val postUseCases: PostUseCases
) : ViewModel() {


}