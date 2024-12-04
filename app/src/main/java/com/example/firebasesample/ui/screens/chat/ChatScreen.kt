package com.example.firebasesample.ui.screens.chat

import androidx.compose.runtime.Composable
import com.example.firebasesample.ui.component.CenteredContainer
import com.example.firebasesample.ui.component.FirebaseSampleButton

@Suppress("ModifierMissing")
@Composable
fun ChatScreen(
    navigateToUserSettings: () -> Unit,
) {
    CenteredContainer {
        FirebaseSampleButton(
            text = "To User Settings",
            onClick = navigateToUserSettings,
        )
    }
}
