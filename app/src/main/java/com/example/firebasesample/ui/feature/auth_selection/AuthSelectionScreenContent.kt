package com.example.firebasesample.ui.feature.auth_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.firebasesample.R
import com.example.firebasesample.ui.component.DisplayMediumText
import com.example.firebasesample.ui.component.FirebaseSampleFilledButton
import com.example.firebasesample.ui.component.FirebaseSampleLottie
import com.example.firebasesample.ui.component.FirebaseSampleOutlinedButton
import com.example.firebasesample.ui.component.TitleLargeText
import com.example.firebasesample.ui.feature.auth_selection.AuthSelectionScreenDimension.HeaderIconSize
import com.example.firebasesample.ui.feature.auth_selection.AuthSelectionScreenDimension.SignInButtonHeight
import com.example.firebasesample.ui.feature.auth_selection.AuthSelectionScreenDimension.SignUpButtonHeight
import com.example.firebasesample.ui.theme.dimensions.Paddings
import com.example.firebasesample.ui.theme.dimensions.Weights
import com.google.android.gms.common.SignInButton

@Composable
fun AuthSelectionScreenContent(
    onEvent: (AuthSelectionUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(Paddings.Large),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuthSelectionHeader(
            modifier = Modifier.weight(Weights.Heavy),
        )
        AuthSelectionButtons(
            onEvent = onEvent,
            modifier = Modifier.weight(Weights.Medium),
        )
    }
}


@Composable
private fun AuthSelectionHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Paddings.Small, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FirebaseSampleLottie(
            resId = R.raw.icon_animation,
            modifier = Modifier.size(HeaderIconSize),
        )
        Column {
            DisplayMediumText(
                text = "Firebase",
            )
            DisplayMediumText(
                text = "Sample",
            )
        }
    }
}

@Composable
private fun AuthSelectionButtons(
    onEvent: (AuthSelectionUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Paddings.ExtraLarge),
    ) {
        SignInButton(
            onClick = { onEvent(AuthSelectionUiEvent.OnSignInButtonClick) },
        )
        SignUpButton(
            onClick = { onEvent(AuthSelectionUiEvent.OnSignUpButtonClick) },
        )
    }
}

@Composable
private fun SignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FirebaseSampleFilledButton(
        text = "ログイン",
        onClick = onClick,
        modifier = modifier
            .height(SignInButtonHeight)
            .fillMaxWidth(),
    )
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FirebaseSampleOutlinedButton(
        text = "新規登録",
        onClick = onClick,
        modifier = modifier
            .height(SignUpButtonHeight)
            .fillMaxWidth(),
        shape = CircleShape,
    )
}
