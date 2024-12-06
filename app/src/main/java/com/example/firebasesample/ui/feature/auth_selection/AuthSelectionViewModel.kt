package com.example.firebasesample.ui.feature.auth_selection

import com.example.firebasesample.ui.base.BaseViewModel
import javax.inject.Inject

class AuthSelectionViewModel @Inject constructor() :
    BaseViewModel<AuthSelectionUiState, AuthSelectionUiEvent, AuthSelectionSideEffect>() {
    override fun createInitialState(): AuthSelectionUiState = AuthSelectionUiState()
}
