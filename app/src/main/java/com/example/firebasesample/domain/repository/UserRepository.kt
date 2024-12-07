package com.example.firebasesample.domain.repository

import com.example.firebasesample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(uid: String): Flow<User>
    suspend fun createUser(user: User): Result<Unit>
    suspend fun updateUser(user: User)
}