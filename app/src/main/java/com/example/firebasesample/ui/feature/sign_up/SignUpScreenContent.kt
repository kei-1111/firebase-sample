package com.example.firebasesample.ui.feature.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.firebasesample.ui.component.CenteredContainer
import com.example.firebasesample.ui.component.DisplayMediumText
import com.example.firebasesample.ui.component.EmailTextField
import com.example.firebasesample.ui.component.FirebaseSampleFilledButton
import com.example.firebasesample.ui.component.FirebaseSampleOutlinedButton
import com.example.firebasesample.ui.component.PasswordTextField
import com.example.firebasesample.ui.feature.sign_up.SignUpScreenDimension.EmailTextFieldHeight
import com.example.firebasesample.ui.feature.sign_up.SignUpScreenDimension.HorizontalDividerFraction
import com.example.firebasesample.ui.feature.sign_up.SignUpScreenDimension.PasswordTextFieldHeight
import com.example.firebasesample.ui.feature.sign_up.SignUpScreenDimension.SubmitButtonHeight
import com.example.firebasesample.ui.theme.dimensions.Paddings
import com.example.firebasesample.ui.theme.dimensions.Weights

@Composable
fun SignUpScreenContent(
    uiState: SignUpUiState,
    onEvent: (SignUpUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(Paddings.Large),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SignUpHeader(
            modifier = Modifier.weight(Weights.Medium),
        )
        SignUpFields(
            uiState = uiState,
            onEvent = onEvent,
            modifier = Modifier.weight(Weights.Heavy),
        )
    }
}

@Composable
private fun SignUpHeader(
    modifier: Modifier = Modifier,
) {
    CenteredContainer(
        modifier = modifier,

        ) {
        DisplayMediumText(
            text = "新規登録",
        )
    }
}

@Composable
private fun SignUpFields(
    uiState: SignUpUiState,
    onEvent: (SignUpUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Paddings.ExtraLarge),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Paddings.Large),
        ) {
            EmailTextField(
                email = uiState.email,
                onEmailChange = { onEvent(SignUpUiEvent.OnEmailInputChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(EmailTextFieldHeight),
            )
            PasswordTextField(
                password = uiState.password,
                onPasswordChange = { onEvent(SignUpUiEvent.OnPasswordInputChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PasswordTextFieldHeight),
            )
        }
        SignUpButton(
            onClick = { onEvent(SignUpUiEvent.OnSignUpButtonClick) },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.email.isNotEmpty() && uiState.password.isNotEmpty(),
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(HorizontalDividerFraction)
                .padding(vertical = Paddings.Large),
        )
        GoogleSignUpButton(
            onClick = { onEvent(SignUpUiEvent.OnGoogleSignInClick) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    FirebaseSampleFilledButton(
        text = "新規登録",
        onClick = onClick,
        modifier = modifier
            .height(SubmitButtonHeight),
        enabled = enabled,
    )
}

@Composable
private fun GoogleSignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FirebaseSampleOutlinedButton(
        text = "Googleで登録",
        onClick = onClick,
        modifier = modifier
            .height(SubmitButtonHeight),
    )
}
