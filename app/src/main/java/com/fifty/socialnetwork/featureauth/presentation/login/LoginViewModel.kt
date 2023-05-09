package com.fifty.socialnetwork.featureauth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifty.socialnetwork.core.domain.states.StandardTextFieldState
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.Screen
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featureauth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _passwordState = mutableStateOf(StandardTextFieldState())
    val passwordState: State<StandardTextFieldState> = _passwordState

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _emailState.value = emailState.value.copy(
                    text = event.email
                )
            }
            is LoginEvent.EnteredPassword -> {
                _passwordState.value = passwordState.value.copy(
                    text = event.password
                )
            }
            LoginEvent.TogglePasswordVisibility -> {
                _loginState.value =
                    _loginState.value.copy(isPasswordVisible = !_loginState.value.isPasswordVisible)
            }
            LoginEvent.Login -> {
                viewModelScope.launch {
                    _loginState.value = loginState.value.copy(isLoading = true)
                    val loginResult = loginUseCase(
                        email = emailState.value.text,
                        password = passwordState.value.text
                    )
                    _loginState.value = loginState.value.copy(isLoading = false)
                    if (loginResult.emailError != null) {
                        _emailState.value = emailState.value.copy(
                            error = loginResult.emailError
                        )
                    }
                    if (loginResult.passwordError != null) {
                        _passwordState.value = passwordState.value.copy(
                            error = loginResult.passwordError
                        )
                    }
                    when (loginResult.result) {
                        is Resource.Success -> {
                            _eventFlow.emit(
                                UiEvent.Navigate(Screen.MainFeedScreen.route)
                            )
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackBar(
                                    loginResult.result.uiText ?: UiText.unknownError()
                                )
                            )
                        }
                        null -> {}
                    }
                }
            }
        }
    }
}