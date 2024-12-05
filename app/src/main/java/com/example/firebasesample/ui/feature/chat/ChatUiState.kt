package com.example.firebasesample.ui.feature.chat

import com.example.firebasesample.ui.base.UiState

data class ChatUiState(
    val message: String = "",
) : UiState
