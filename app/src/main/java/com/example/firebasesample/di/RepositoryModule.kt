package com.example.firebasesample.di

import com.example.firebasesample.data.repository.AuthRepositoryImpl
import com.example.firebasesample.data.repository.ChatRepositoryImpl
import com.example.firebasesample.data.repository.UserRepositoryImpl
import com.example.firebasesample.domain.repository.AuthRepository
import com.example.firebasesample.domain.repository.ChatRepository
import com.example.firebasesample.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): AuthRepository = AuthRepositoryImpl(auth, ioDispatcher)

    @Provides
    @Singleton
    fun provideChatRepository(
        firestore: FirebaseFirestore,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): ChatRepository = ChatRepositoryImpl(firestore, ioDispatcher)

    @Provides
    @Singleton
    fun provideUserRepository(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): UserRepository = UserRepositoryImpl(firestore, auth, ioDispatcher)
}
