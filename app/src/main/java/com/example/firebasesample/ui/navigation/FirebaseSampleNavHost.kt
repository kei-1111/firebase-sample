package com.example.firebasesample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebasesample.ui.screens.chat.ChatScreen
import com.example.firebasesample.ui.screens.login.LoginScreen
import com.example.firebasesample.ui.screens.user_settings.UserSettingsScreen

@Composable
fun FirebaseSampleNavHost(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<Screen.Login> {
            LoginScreen(
                navigateToChat = { navController.navigateToChat() },
            )
        }

        composable<Screen.Chat> {
            ChatScreen(
                navigateToUserSettings = { navController.navigateToUserSettings() },
            )
        }

        composable<Screen.UserSettings> {
            UserSettingsScreen(
                navigateToChat = { navController.navigateToChat() },
                navigateToLogin = { navController.navigateToLogin() },
            )
        }
    }
}

fun NavHostController.navigateToLogin() {
    this.navigate(Screen.Login)
}

fun NavHostController.navigateToChat() {
    this.navigate(Screen.Chat)
}

fun NavHostController.navigateToUserSettings() {
    this.navigate(Screen.UserSettings)
}
