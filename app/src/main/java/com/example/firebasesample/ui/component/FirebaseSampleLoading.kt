package com.example.firebasesample.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.firebasesample.R
import com.example.firebasesample.ui.theme.dimensions.Alpha

@Composable
fun FirebaseSampleLoading(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loading_animation),
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.scrim
            .copy(alpha = Alpha.High),
    ) {
        CenteredContainer {
            LottieAnimation(
                composition = composition,
                progress = progress,
            )
        }
    }
}
