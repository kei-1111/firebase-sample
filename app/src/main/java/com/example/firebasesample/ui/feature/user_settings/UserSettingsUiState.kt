package com.example.firebasesample.ui.feature.user_settings

import com.example.firebasesample.domain.model.User
import com.example.firebasesample.ui.base.UiState

data class UserSettingsUiState(
    val name: String = "",
    val user: User = User()
) : UiState
