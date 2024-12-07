package com.example.firebasesample.ui.feature.user_settings

import androidx.lifecycle.viewModelScope
import com.example.firebasesample.domain.repository.AuthRepository
import com.example.firebasesample.domain.repository.UserRepository
import com.example.firebasesample.domain.use_case.GetCurrentUserUseCase
import com.example.firebasesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel<UserSettingsUiState, UserSettingsUiEvent, UserSettingsSideEffect>() {

    override fun createInitialState(): UserSettingsUiState = UserSettingsUiState()

    init {
        fetchCurrentUser()
    }

    private fun fetchCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase.invoke()
                .collect { user ->
                    updateUiState { it.copy(name = user.name, user = user) }
                }
        }
    }

    fun updateName(name: String) {
        updateUiState { it.copy(name = name) }
    }

    fun saveUser() {
        val name = _uiState.value.name
        val user = _uiState.value.user.copy(name = name)
        viewModelScope.launch {
            userRepository.updateUser(user)
            sendEffect(UserSettingsSideEffect.ShowToast("Name updated"))
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
            sendEffect(UserSettingsSideEffect.NavigateToAuthSelection)
        }
    }
}