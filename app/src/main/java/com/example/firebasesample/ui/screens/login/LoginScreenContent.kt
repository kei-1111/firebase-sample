package com.example.firebasesample.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.firebasesample.ui.component.DisplayMediumText
import com.example.firebasesample.ui.component.EmailTextField
import com.example.firebasesample.ui.component.FirebaseSampleFilledButton
import com.example.firebasesample.ui.component.FirebaseSampleOutlinedButton
import com.example.firebasesample.ui.component.PasswordTextField
import com.example.firebasesample.ui.screens.login.LoginScreenDimension.EmailTextFieldHeight
import com.example.firebasesample.ui.screens.login.LoginScreenDimension.HorizontalDividerFraction
import com.example.firebasesample.ui.screens.login.LoginScreenDimension.PasswordTextFieldHeight
import com.example.firebasesample.ui.screens.login.LoginScreenDimension.SubmitButtonHeight
import com.example.firebasesample.ui.theme.dimensions.Paddings
import com.example.firebasesample.ui.theme.dimensions.Weights

@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(Paddings.Large),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoginHeader(
            modifier = Modifier.weight(Weights.Medium),
        )
        LoginFields(
            uiState = uiState,
            onEvent = onEvent,
            modifier = Modifier.weight(Weights.Heavy),
        )
    }
}

@Composable
private fun LoginHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        DisplayMediumText(
            text = "Firebase Sample",
        )
        DisplayMediumText(
            text = "ログイン",
        )
    }
}

@Composable
private fun LoginFields(
    uiState: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
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
                onEmailChange = { onEvent(LoginUiEvent.OnEmailInputChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(EmailTextFieldHeight),
            )
            PasswordTextField(
                password = uiState.password,
                onPasswordChange = { onEvent(LoginUiEvent.OnPasswordInputChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PasswordTextFieldHeight),
            )
        }
        LoginButton(
            onClick = { onEvent(LoginUiEvent.OnLoginButtonClick) },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.email.isNotEmpty() && uiState.password.isNotEmpty(),
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(HorizontalDividerFraction)
                .padding(vertical = Paddings.Large),
        )
        GoogleSignInButton(
            onClick = { onEvent(LoginUiEvent.OnGoogleSignInClick) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun LoginButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    FirebaseSampleFilledButton(
        text = "ログイン",
        onClick = onClick,
        modifier = modifier
            .height(SubmitButtonHeight),
        enabled = enabled,
    )
}

@Composable
private fun GoogleSignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FirebaseSampleOutlinedButton(
        text = "Googleでログイン",
        onClick = onClick,
        modifier = modifier
            .height(SubmitButtonHeight),
    )
}
