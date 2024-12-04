package com.example.firebasesample.ui.screens.login

import androidx.compose.runtime.Composable
import com.example.firebasesample.ui.component.CenteredContainer
import com.example.firebasesample.ui.component.FirebaseSampleButton

@Suppress("ModifierMissing")
@Composable
fun LoginScreen(
    navigateToChat: () -> Unit,
) {
    CenteredContainer {
        FirebaseSampleButton(
            text = "To Chat",
            onClick = navigateToChat,
        )
    }
}
