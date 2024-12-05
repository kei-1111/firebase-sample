package com.example.firebasesample.ui.screens.register

import com.example.firebasesample.ui.base.UiEffect

interface RegisterUiEffect : UiEffect {
    data class ShowToast(val message: String) : RegisterUiEffect
    object NavigateToChat : RegisterUiEffect
}
