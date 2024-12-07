package com.example.firebasesample.domain.use_case

import com.example.firebasesample.di.IoDispatcher
import com.example.firebasesample.domain.model.User
import com.example.firebasesample.domain.repository.AuthRepository
import com.example.firebasesample.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) : GetCurrentUserUseCase {
    override suspend fun invoke(): Flow<User> {
        val uid = authRepository.getCurrentUser()?.uid ?: ""
        return userRepository.getUser(uid)
    }
}