package com.example.firebasesample.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.firebasesample.R
import com.example.firebasesample.ui.theme.dimensions.Alpha

@Composable
fun FirebaseSampleLoading(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.scrim
            .copy(alpha = Alpha.High),
    ) {
        CenteredContainer {
            FirebaseSampleLottie(
                resId = R.raw.loading_animation,
            )
        }
    }
}
