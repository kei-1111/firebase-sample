package com.example.firebasesample.ui.feature.user_settings

import com.example.firebasesample.ui.base.UiEvent

sealed interface UserSettingsUiEvent : UiEvent {
    data object OnNavigateToChat : UserSettingsUiEvent
    data class OnNameChange(val name: String) : UserSettingsUiEvent
    data object OnSignOutButtonClick : UserSettingsUiEvent
}
