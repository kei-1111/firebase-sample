package com.example.firebasesample.ui.screens.register

import androidx.lifecycle.viewModelScope
import com.example.firebasesample.domain.repository.AuthRepository
import com.example.firebasesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel<RegisterUiState, RegisterUiEvent, RegisterUiEffect>() {
    override fun createInitialState(): RegisterUiState = RegisterUiState()

    fun updateEmail(email: String) {
        updateUiState { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        updateUiState { it.copy(password = password) }
    }

    fun submitRegister() {
        viewModelScope.launch {
            updateUiState { it.copy(isLoading = true) }
            val email = uiState.value.email
            val password = uiState.value.password
            val result = authRepository.register(email, password)
            if (result.isSuccess) {
                updateUiState { it.copy(isLoading = false) }
                sendEffect(RegisterUiEffect.NavigateToChat)
            } else {
                updateUiState { it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "") }
                sendEffect(RegisterUiEffect.ShowToast("Register failed"))
            }
        }
    }

    fun startGoogleSignIn() {
    }
}
