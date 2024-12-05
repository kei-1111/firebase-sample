package com.example.firebasesample.ui.feature.sign_in

import com.example.firebasesample.ui.base.UiState

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
) : UiState
