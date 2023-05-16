package com.fifty.socialnetwork.featureprofile.presentation.search

import com.fifty.socialnetwork.core.util.Error
import com.fifty.socialnetwork.core.util.UiText

data class SearchError(
    val message: UiText
) : Error()
