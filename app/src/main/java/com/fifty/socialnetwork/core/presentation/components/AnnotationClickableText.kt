package com.fifty.socialnetwork.core.presentation.components

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun AnnotatedClickableText(

) {
    val annotatedText = buildAnnotatedString {
        pushStringAnnotation(
            tag = "username",
            annotation = "username"
        )

        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold)
        ) {
            append("Florian")
        }



        withStyle(
            style = SpanStyle(
                color = Color.Red
            )
        ) {
            append("Sign Up")
        }

        pop()

    }
    ClickableText(text = annotatedText, onClick = { offset ->
        annotatedText.getStringAnnotations(
            tag = "SignUp",
            start = offset,
            end = offset
        ).firstOrNull().let { annotation ->
            Log.d("Clicked", annotation?.item ?: "")
        }
    })
}