package com.example.firebasesample.ui.screens.register

import com.example.firebasesample.ui.base.UiState

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
) : UiState
