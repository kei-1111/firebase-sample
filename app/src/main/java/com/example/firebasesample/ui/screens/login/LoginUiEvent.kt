package com.example.firebasesample.ui.screens.login

import com.example.firebasesample.ui.base.UiEvent

sealed interface LoginUiEvent : UiEvent {
    data class OnEmailInputChange(val email: String) : LoginUiEvent
    data class OnPasswordInputChange(val password: String) : LoginUiEvent
    data object OnLoginButtonClick : LoginUiEvent
    data object OnGoogleSignInClick : LoginUiEvent
}
