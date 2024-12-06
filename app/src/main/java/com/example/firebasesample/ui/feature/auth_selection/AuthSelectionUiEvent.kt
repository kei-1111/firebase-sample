package com.example.firebasesample.ui.feature.auth_selection

import com.example.firebasesample.ui.base.UiEvent

sealed interface AuthSelectionUiEvent : UiEvent {
    data object OnSignInButtonClick : AuthSelectionUiEvent
    data object OnSignUpButtonClick : AuthSelectionUiEvent
}
