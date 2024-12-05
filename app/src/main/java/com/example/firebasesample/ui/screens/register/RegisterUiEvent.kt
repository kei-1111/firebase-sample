package com.example.firebasesample.ui.screens.register

import com.example.firebasesample.ui.base.UiEvent

sealed interface RegisterUiEvent : UiEvent {
    data class OnEmailInputChange(val email: String) : RegisterUiEvent
    data class OnPasswordInputChange(val password: String) : RegisterUiEvent
    data object OnRegisterButtonClick : RegisterUiEvent
    data object OnGoogleSignInClick : RegisterUiEvent
}
