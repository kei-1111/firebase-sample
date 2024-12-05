package com.example.firebasesample.ui.screens.login

import com.example.firebasesample.ui.base.UiState

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
) : UiState
