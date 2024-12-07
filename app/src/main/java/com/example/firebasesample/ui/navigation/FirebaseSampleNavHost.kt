package com.example.firebasesample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebasesample.ui.feature.auth_selection.AuthSelectionScreen
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
        composable<Screen.AuthSelection> {
            AuthSelectionScreen(
                navigateToSignUp = { navController.navigateToSignUp() },
                navigateToSignIn = { navController.navigateToSignIn() },
            )
        }

        composable<Screen.SignUp> {
            SignUpScreen(
                navigateToChat = { navController.navigateToChat() },
            )
        }

        composable<Screen.SingIn> {
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
                navigateToAuthSelection = { navController.navigateToAuthSelection() },
            )
        }
    }
}

fun NavHostController.navigateToAuthSelection() {
    this.navigate(Screen.AuthSelection)
}

fun NavHostController.navigateToSignUp() {
    this.navigate(Screen.SignUp)
}

fun NavHostController.navigateToSignIn() {
    this.navigate(Screen.SingIn)
}

fun NavHostController.navigateToChat() {
    this.navigate(Screen.Chat)
}

fun NavHostController.navigateToUserSettings() {
    this.navigate(Screen.UserSettings)
}
