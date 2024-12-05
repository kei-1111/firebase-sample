package com.example.firebasesample.ui.screens.chat

import com.example.firebasesample.ui.base.UiEvent

sealed interface ChatUiEvent : UiEvent {
    data class OnMessageInputChange(val message: String) : ChatUiEvent
    data object OnMessageSendClick : ChatUiEvent
    data object OnUserSettingsButtonClick : ChatUiEvent
}
