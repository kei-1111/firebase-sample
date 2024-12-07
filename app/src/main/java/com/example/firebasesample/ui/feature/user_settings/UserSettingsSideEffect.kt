package com.example.firebasesample.ui.feature.user_settings

import com.example.firebasesample.ui.base.SideEffect

sealed interface UserSettingsSideEffect : SideEffect {
    data class ShowToast(val message: String) : UserSettingsSideEffect
    data object NavigateToAuthSelection : UserSettingsSideEffect
}
