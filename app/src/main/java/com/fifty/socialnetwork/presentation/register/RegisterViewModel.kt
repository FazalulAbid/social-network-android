package com.fifty.socialnetwork.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RegisterViewModel @Inject constructor(

) : ViewModel() {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _isUsernameError = mutableStateOf(false)
    val isUsernameError: State<Boolean> = _isUsernameError

    private val _isPasswordError = mutableStateOf(false)
    val isPasswordError: State<Boolean> = _isPasswordError
}