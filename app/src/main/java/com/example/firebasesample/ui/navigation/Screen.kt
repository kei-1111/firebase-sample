package com.example.firebasesample.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object AuthSelection : Screen

    @Serializable
    data object SignUp : Screen

    @Serializable
    data object SingIn : Screen

    @Serializable
    data object Chat : Screen

    @Serializable
    data object UserSettings : Screen
}
