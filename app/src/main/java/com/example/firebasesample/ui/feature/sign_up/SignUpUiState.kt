package com.example.firebasesample.ui.feature.sign_up

import com.example.firebasesample.ui.base.UiState

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
) : UiState
