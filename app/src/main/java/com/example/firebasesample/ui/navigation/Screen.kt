package com.example.firebasesample.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen() {
    @Serializable
    data object Login : Screen()

    @Serializable
    data object Chat : Screen()

    @Serializable
    data object UserSettings : Screen()
}

