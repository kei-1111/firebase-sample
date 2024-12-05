package com.example.firebasesample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasesample.domain.repository.AuthRepository
import com.example.firebasesample.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Screen?>(null)
    val startDestination: StateFlow<Screen?> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            val isUserLoggedIn = authRepository.isUserLoggedIn()
            _startDestination.value = if (isUserLoggedIn) {
                Screen.Chat
            } else {
                Screen.Register
            }
        }
    }
}
