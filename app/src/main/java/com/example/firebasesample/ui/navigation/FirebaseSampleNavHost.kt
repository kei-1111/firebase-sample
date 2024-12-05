package com.example.firebasesample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebasesample.ui.feature.chat.ChatScreen
import com.example.firebasesample.ui.feature.sign_in.SignInScreen
import com.example.firebasesample.ui.feature.sign_up.SignUpScreen
import com.example.firebasesample.ui.feature.user_settings.UserSettingsScreen

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
        composable<Screen.Register> {
            SignUpScreen(
                navigateToChat = { navController.navigateToChat() },
            )
        }

        composable<Screen.Login> {
            SignInScreen(
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
