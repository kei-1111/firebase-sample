package com.example.firebasesample.ui.screens.register

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
import com.example.firebasesample.ui.screens.register.RegisterScreenDimension.EmailTextFieldHeight
import com.example.firebasesample.ui.screens.register.RegisterScreenDimension.HorizontalDividerFraction
import com.example.firebasesample.ui.screens.register.RegisterScreenDimension.PasswordTextFieldHeight
import com.example.firebasesample.ui.screens.register.RegisterScreenDimension.SubmitButtonHeight
import com.example.firebasesample.ui.theme.dimensions.Paddings
import com.example.firebasesample.ui.theme.dimensions.Weights

@Composable
fun RegisterScreenContent(
    uiState: RegisterUiState,
    onEvent: (RegisterUiEvent) -> Unit,
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
        RegisterFields(
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
            text = "新規登録",
        )
    }
}

@Composable
private fun RegisterFields(
    uiState: RegisterUiState,
    onEvent: (RegisterUiEvent) -> Unit,
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
                onEmailChange = { onEvent(RegisterUiEvent.OnEmailInputChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(EmailTextFieldHeight),
            )
            PasswordTextField(
                password = uiState.password,
                onPasswordChange = { onEvent(RegisterUiEvent.OnPasswordInputChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(PasswordTextFieldHeight),
            )
        }
        RegisterButton(
            onClick = { onEvent(RegisterUiEvent.OnRegisterButtonClick) },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.email.isNotEmpty() && uiState.password.isNotEmpty(),
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(HorizontalDividerFraction)
                .padding(vertical = Paddings.Large),
        )
        GoogleSignInButton(
            onClick = { onEvent(RegisterUiEvent.OnGoogleSignInClick) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun RegisterButton(
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
private fun GoogleSignInButton(
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
