package com.fifty.socialnetwork.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.presentation.ui.theme.IconSizeMedium

@Composable
fun StandardTextField(
    text: String = "",
    hint: String = "",
    maxLength: Int = 400,
    error: String = "",
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground
    ),
    backgroundColor: Color = MaterialTheme.colors.surface,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    onValueChange: (String) -> Unit,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    showPasswordToggle: Boolean = false,
    modifier: Modifier,
    focusRequester: FocusRequester = FocusRequester()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        TextField(
            value = text,
            onValueChange = {
                if (it.length < maxLength) {
                    onValueChange(it)
                }
            },
            textStyle = textStyle,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor
            ),
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1
                )
            },
            isError = error != "",
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation = if (!showPasswordToggle && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            singleLine = singleLine,
            maxLines = maxLines,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        modifier = Modifier.size(IconSizeMedium),
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            } else null,
            trailingIcon = if (isPasswordToggleDisplayed) {
                {
                    IconButton(onClick = {
                        onPasswordToggleClick(!showPasswordToggle)
                    }) {
                        Icon(
                            imageVector = if (showPasswordToggle) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            contentDescription = if (showPasswordToggle) {
                                stringResource(id = R.string.password_visible_content_description)
                            } else {
                                stringResource(id = R.string.password_hidden_content_description)
                            }
                        )
                    }
                }
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester),
        )
        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}