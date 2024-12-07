package com.example.firebasesample.domain.repository

import com.example.firebasesample.domain.model.TextMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getMessages(): Flow<List<TextMessage>>
    suspend fun sendTextMessage(message: TextMessage)
}
