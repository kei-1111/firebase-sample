package com.example.firebasesample.data.repository

import com.example.firebasesample.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher,
) : AuthRepository {
    override suspend fun register(email: String, password: String): Result<FirebaseUser> =
        withContext(ioDispatcher) {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                Result.success(result.user!!)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun login(email: String, password: String): Result<FirebaseUser> =
        withContext(ioDispatcher) {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                Result.success(result.user!!)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun logout(): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return withContext(ioDispatcher) {
            auth.currentUser != null
        }
    }
}
