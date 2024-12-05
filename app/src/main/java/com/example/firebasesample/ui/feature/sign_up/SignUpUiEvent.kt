package com.example.firebasesample.ui.feature.sign_up

import com.example.firebasesample.ui.base.UiEvent

sealed interface SignUpUiEvent : UiEvent {
    data class OnEmailInputChange(val email: String) : SignUpUiEvent
    data class OnPasswordInputChange(val password: String) : SignUpUiEvent
    data object OnSignUpButtonClick : SignUpUiEvent
    data object OnGoogleSignInClick : SignUpUiEvent
}
