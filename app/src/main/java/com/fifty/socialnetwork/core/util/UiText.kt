package com.fifty.socialnetwork.core.util

import android.content.Context
import androidx.annotation.StringRes
import com.fifty.socialnetwork.R

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(@StringRes val id: Int) : UiText()

    companion object {
        fun unknownError(): UiText {
            return StringResource(R.string.error_unknown)
        }
    }
}
