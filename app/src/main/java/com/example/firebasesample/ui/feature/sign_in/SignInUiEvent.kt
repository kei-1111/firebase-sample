package com.example.firebasesample.ui.feature.sign_in

import com.example.firebasesample.ui.base.UiEvent

sealed interface SignInUiEvent : UiEvent {
    data class OnEmailInputChange(val email: String) : SignInUiEvent
    data class OnPasswordInputChange(val password: String) : SignInUiEvent
    data object OnSignInButtonClick : SignInUiEvent
    data object OnGoogleSignInClick : SignInUiEvent
}
