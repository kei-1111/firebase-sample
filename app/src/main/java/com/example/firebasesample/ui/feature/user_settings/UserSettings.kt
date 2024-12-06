package com.example.firebasesample.ui.feature.user_settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.firebasesample.ui.component.CenteredContainer
import com.example.firebasesample.ui.component.FirebaseSampleFilledButton

@Suppress("ModifierMissing")
@Composable
fun UserSettingsScreen(
    navigateToChat: () -> Unit,
    navigateToAuthSelection: () -> Unit,
) {
    CenteredContainer {
        Column {
            FirebaseSampleFilledButton(
                text = "To Chat",
                onClick = navigateToChat,
            )
            FirebaseSampleFilledButton(
                text = "To Login",
                onClick = navigateToAuthSelection,
            )
        }
    }
}
