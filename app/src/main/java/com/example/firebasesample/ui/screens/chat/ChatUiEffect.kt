package com.example.firebasesample.ui.screens.chat

import com.example.firebasesample.ui.base.UiEffect

sealed interface ChatUiEffect : UiEffect {
    data class ShowToast(val message: String) : ChatUiEffect
}
