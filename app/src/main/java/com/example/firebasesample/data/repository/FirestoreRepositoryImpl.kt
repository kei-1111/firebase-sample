package com.example.firebasesample.data.repository

import com.example.firebasesample.di.IoDispatcher
import com.example.firebasesample.domain.model.TextMessage
import com.example.firebasesample.domain.repository.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FirestoreRepository {
    private val chatCollection = firestore.collection("chats")

    override suspend fun sendTextMessage(message: TextMessage) {
        withContext(ioDispatcher) {
            chatCollection.document(message.id).set(message).await()
        }
    }

    override fun getMessages(): Flow<List<TextMessage>> = callbackFlow {
        val listener = chatCollection
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val messages =
                    snapshot?.documents?.mapNotNull { it.toObject(TextMessage::class.java) }
                trySend(messages ?: emptyList())
            }
        awaitClose { listener.remove() }
    }
}
