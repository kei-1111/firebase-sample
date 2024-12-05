package com.example.firebasesample.ui.feature.chat

import com.example.firebasesample.ui.base.SideEffect

sealed interface ChatSideEffect : SideEffect {
    data class ShowToast(val message: String) : ChatSideEffect
}
