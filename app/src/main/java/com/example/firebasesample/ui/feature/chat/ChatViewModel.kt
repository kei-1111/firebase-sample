package com.example.firebasesample.ui.feature.chat

import androidx.lifecycle.viewModelScope
import com.example.firebasesample.domain.model.TextMessage
import com.example.firebasesample.domain.model.User
import com.example.firebasesample.domain.repository.ChatRepository
import com.example.firebasesample.domain.use_case.GetCurrentUserUseCase
import com.example.firebasesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel<ChatUiState, ChatUiEvent, ChatSideEffect>() {
    override fun createInitialState(): ChatUiState = ChatUiState()

    private val _currentUser = MutableStateFlow(User())

    init {
        fetchCurrentUser()
    }

    private fun fetchCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase.invoke()
                .collect { user ->
                    _currentUser.update { user }
                }
        }
    }

    val messages: StateFlow<List<TextMessage>> = chatRepository.getMessages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    fun updateMessage(message: String) {
        updateUiState { it.copy(message = message) }
    }

    fun sendTextMessage() {
        val user = _currentUser.value

        viewModelScope.launch {
            val message = _uiState.value.message
            val textMessage = TextMessage(
                id = UUID.randomUUID().toString(),
                user = user,
                message = message,
            )
            updateUiState { it.copy(message = "") }
            chatRepository.sendTextMessage(textMessage)
        }
    }
}
