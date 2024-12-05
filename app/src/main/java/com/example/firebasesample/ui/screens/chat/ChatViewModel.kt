package com.example.firebasesample.ui.screens.chat

import androidx.lifecycle.viewModelScope
import com.example.firebasesample.domain.model.TextMessage
import com.example.firebasesample.domain.repository.FirestoreRepository
import com.example.firebasesample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
) : BaseViewModel<ChatUiState, ChatUiEvent, ChatUiEffect>() {
    override fun createInitialState(): ChatUiState = ChatUiState()

    val messages: StateFlow<List<TextMessage>> = firestoreRepository.getMessages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun updateMessage(message: String) {
        updateUiState { it.copy(message = message) }
    }

    fun sendTextMessage(userId: String) {
        viewModelScope.launch {
            val message = _uiState.value.message
            val textMessage = TextMessage(
                id = UUID.randomUUID().toString(),
                senderId = userId,
                message = message
            )
            firestoreRepository.sendTextMessage(textMessage)
            updateUiState { it.copy(message = "") }
        }
    }
}
