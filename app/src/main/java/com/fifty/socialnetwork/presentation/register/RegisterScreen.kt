package com.fifty.socialnetwork.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.presentation.components.StandardTextField
import com.fifty.socialnetwork.presentation.ui.theme.SpaceLarge
import com.fifty.socialnetwork.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.presentation.util.Screen

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(
//                start = SpaceLarge,
//                end = SpaceLarge,
//                top = SpaceLarge,
//                bottom = 50.dp
//            )
//    ) {
//        Column(
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(SpaceMedium)
//                .align(Alignment.Center)
//        ) {
//            Text(
//                text = stringResource(R.string.login),
//                color = MaterialTheme.colors.onBackground,
//                style = MaterialTheme.typography.h1
//            )
//            Spacer(modifier = Modifier.height(SpaceMedium))
//            StandardTextField(
//                text = viewModel.usernameText.value,
//                onValueChange = {
//                    viewModel.setUsernameText(it)
//                }, hint = stringResource(R.string.login_hint)
//            )
//            Spacer(modifier = Modifier.height(SpaceMedium))
//            StandardTextField(
//                text = viewModel.passwordText.value,
//                onValueChange = {
//                    viewModel.setPasswordText(it)
//                },
//                hint = stringResource(id = R.string.password_hint),
//                keyboardType = KeyboardType.Password,
//            )
//        }
//        Text(
//            text = buildAnnotatedString {
//                append(stringResource(R.string.dont_have_an_account_yet))
//                append("")
//                val signUpText = stringResource(id = R.string.sign_up)
//                withStyle(
//                    style = SpanStyle(
//                        color = MaterialTheme.colors.primary
//                    )
//                ) {
//                    append(signUpText)
//                }
//            },
//            style = MaterialTheme.typography.body1,
//            modifier = Modifier.align(Alignment.BottomCenter)
//        )
//    }
}