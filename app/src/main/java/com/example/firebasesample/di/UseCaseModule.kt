package com.example.firebasesample.di

import com.example.firebasesample.domain.repository.AuthRepository
import com.example.firebasesample.domain.repository.UserRepository
import com.example.firebasesample.domain.use_case.GetCurrentUserUseCase
import com.example.firebasesample.domain.use_case.GetCurrentUserUseCaseImpl
import com.example.firebasesample.domain.use_case.SignUpWithEmailUseCase
import com.example.firebasesample.domain.use_case.SignUpWithEmailUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideSignUpWithEmailUseCase(
        authRepository: AuthRepository,
        userRepository: UserRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): SignUpWithEmailUseCase = SignUpWithEmailUseCaseImpl(authRepository, userRepository, ioDispatcher)

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(
        authRepository: AuthRepository,
        userRepository: UserRepository,
    ): GetCurrentUserUseCase = GetCurrentUserUseCaseImpl(authRepository, userRepository)
}
