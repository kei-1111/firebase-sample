package com.example.firebasesample.domain.repository

import com.example.firebasesample.domain.model.TextMessage
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    suspend fun sendTextMessage(message: TextMessage)
    fun getMessages(): Flow<List<TextMessage>>
}
