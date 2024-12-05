package com.example.firebasesample.ui.screens.login

import com.example.firebasesample.ui.base.UiEffect

interface LoginUiEffect : UiEffect {
    data class ShowToast(val message: String) : LoginUiEffect
    object NavigateToChat : LoginUiEffect
}
