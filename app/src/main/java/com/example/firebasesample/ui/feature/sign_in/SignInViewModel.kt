package com.example.firebasesample.ui.feature.sign_in

import androidx.lifecycle.viewModelScope
import com.example.firebasesample.domain.repository.AuthRepository
import com.example.firebasesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel<SignInUiState, SignInUiEvent, SignInSideEffect>() {

    override fun createInitialState(): SignInUiState = SignInUiState()

    fun updateEmail(email: String) {
        updateUiState { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        updateUiState { it.copy(password = password) }
    }

    fun submitLogin() {
        viewModelScope.launch {
            updateUiState { it.copy(isLoading = true) }
            val email = uiState.value.email
            val password = uiState.value.password
            val result = authRepository.login(email, password)
            if (result.isSuccess) {
                updateUiState { it.copy(isLoading = false) }
                sendEffect(SignInSideEffect.NavigateToChat)
            } else {
                updateUiState { it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "") }
                sendEffect(SignInSideEffect.ShowToast("Login failed"))
            }
        }
    }

    fun startGoogleSignIn() {
    }
}
