package com.example.firebasesample.ui.screens.user_settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.firebasesample.ui.component.CenteredContainer
import com.example.firebasesample.ui.component.FirebaseSampleButton

@Suppress("ModifierMissing")
@Composable
fun UserSettingsScreen(
    navigateToChat: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    CenteredContainer {
        Column {
            FirebaseSampleButton(
                text = "To Chat",
                onClick = navigateToChat,
            )
            FirebaseSampleButton(
                text = "To Login",
                onClick = navigateToLogin,
            )
        }
    }
}
