package com.example.firebasesample.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FirebaseSampleButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
    ) {
        Text(
            text,
        )
    }
}