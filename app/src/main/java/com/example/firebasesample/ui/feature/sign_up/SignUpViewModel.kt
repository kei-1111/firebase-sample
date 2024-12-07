package com.example.firebasesample.ui.feature.sign_up

import androidx.lifecycle.viewModelScope
import com.example.firebasesample.domain.use_case.SignUpWithEmailUseCase
import com.example.firebasesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
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
            val email = _uiState.value.email
            val password = _uiState.value.password
            val result = signUpWithEmailUseCase(email, password)
            if (result.isSuccess) {
                updateUiState { it.copy(isLoading = false) }
                sendEffect(SignUpSideEffect.NavigateToChat)
            } else {
                updateUiState { it.copy(isLoading = false) }
                sendEffect(SignUpSideEffect.ShowToast(result.exceptionOrNull()?.message ?: "Unknown error"))
            }
        }
    }

    fun startGoogleSignIn() {
    }
}
