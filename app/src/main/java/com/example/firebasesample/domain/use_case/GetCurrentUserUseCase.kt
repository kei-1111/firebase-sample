package com.example.firebasesample.domain.use_case

import com.example.firebasesample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetCurrentUserUseCase {
    suspend operator fun invoke(): Flow<User>
}
