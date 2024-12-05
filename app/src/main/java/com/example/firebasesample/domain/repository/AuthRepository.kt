package com.example.firebasesample.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun register(email: String, password: String): Result<FirebaseUser>
    suspend fun login(email: String, password: String): Result<FirebaseUser>
    suspend fun logout(): Result<Unit>
    suspend fun isUserLoggedIn(): Boolean
}
