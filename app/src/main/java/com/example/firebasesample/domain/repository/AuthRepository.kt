package com.example.firebasesample.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Result<FirebaseUser>
    suspend fun signIn(email: String, password: String): Result<FirebaseUser>
    suspend fun signOut(): Result<Unit>
    suspend fun getCurrentUser(): FirebaseUser?
}
