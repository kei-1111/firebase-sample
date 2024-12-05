package com.example.firebasesample.ui.feature.sign_up

import androidx.lifecycle.viewModelScope
import com.example.firebasesample.domain.repository.AuthRepository
import com.example.firebasesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel<SignUpUiState, SignUpUiEvent, SignUpSideEffect>() {
    override fun createInitialState(): SignUpUiState = SignUpUiState()

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
                sendEffect(SignUpSideEffect.NavigateToChat)
            } else {
                updateUiState { it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "") }
                sendEffect(SignUpSideEffect.ShowToast("Register failed"))
            }
        }
    }

    fun startGoogleSignIn() {
    }
}
