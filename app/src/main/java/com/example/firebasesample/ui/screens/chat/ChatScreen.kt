package com.example.firebasesample.ui.screens.chat

import androidx.compose.material3.Button
import androidx.compose.material3.Text
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