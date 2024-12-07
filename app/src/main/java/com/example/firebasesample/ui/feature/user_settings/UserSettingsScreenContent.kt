package com.example.firebasesample.ui.feature.user_settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.firebasesample.ui.component.FirebaseSampleOutlinedButton
import com.example.firebasesample.ui.component.NameTextField
import com.example.firebasesample.ui.feature.user_settings.UserSettingsScreenDimension.NameTextFieldHeight
import com.example.firebasesample.ui.feature.user_settings.UserSettingsScreenDimension.SignOutButtonHeight
import com.example.firebasesample.ui.theme.dimensions.Paddings

@Composable
fun UserSettingsScreenContent(
    uiState: UserSettingsUiState,
    onEvent: (UserSettingsUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(Paddings.Large),
        verticalArrangement = Arrangement.spacedBy(Paddings.ExtraLarge),
    ) {
        NameTextField(
            name = uiState.name,
            onNameChange = { onEvent(UserSettingsUiEvent.OnNameChange(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(NameTextFieldHeight),
        )

        SignOutButton(
            email = uiState.user.email,
            onSignOutButtonClick = { onEvent(UserSettingsUiEvent.OnSignOutButtonClick) },
            modifier = Modifier
                .fillMaxWidth()
                .height(SignOutButtonHeight),
        )
    }
}

@Composable
private fun SignOutButton(
    email: String,
    onSignOutButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FirebaseSampleOutlinedButton(
        text = email + "からログアウト",
        onClick = onSignOutButtonClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        contentColor = MaterialTheme.colorScheme.error,
    )
}
