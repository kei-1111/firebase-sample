package com.example.firebasesample.data.repository

import com.example.firebasesample.di.IoDispatcher
import com.example.firebasesample.domain.model.User
import com.example.firebasesample.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {
    private val userCollection = firestore.collection("users")

    override fun getUser(uid: String): Flow<User> = callbackFlow {
        val docRef = firestore.collection("users").document(uid)
        val listener = docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error) // エラー発生時は Flow を閉じる
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val user = snapshot.toObject(User::class.java) // Firestore のドキュメントを User に変換
                if (user != null) {
                    trySend(user).isSuccess // User を Flow に送信
                }
            }
        }
        awaitClose { listener.remove() } // Listener を削除
    }.flowOn(ioDispatcher)

    override suspend fun createUser(user: User): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                userCollection.document(user.uid).set(user).await()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun updateUser(user: User) {
        withContext(ioDispatcher) {
            userCollection.document(user.uid).update(
                mapOf(
                    "email" to user.email,
                    "name" to user.name,
                ),
            ).await()
        }
    }
}
