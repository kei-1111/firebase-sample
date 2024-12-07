package com.example.firebasesample.ui.feature.user_settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.firebasesample.ui.component.FirebaseSampleIconButton
import com.example.firebasesample.ui.component.FirebaseSampleTopBar
import com.example.firebasesample.ui.component.TitleLargeText
import com.example.firebasesample.ui.utils.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Suppress("ModifierMissing")
@Composable
fun UserSettingsScreen(
    navigateToChat: () -> Unit,
    navigateToAuthSelection: () -> Unit,
    viewModel: UserSettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    val latestNavigateToChat by rememberUpdatedState(navigateToChat)

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { event ->
            when (event) {
                is UserSettingsUiEvent.OnNavigateToChat -> {
                    viewModel.saveUser()
                    latestNavigateToChat()
                }
                is UserSettingsUiEvent.OnNameChange -> viewModel.updateName(event.name)
                is UserSettingsUiEvent.OnSignOutButtonClick -> viewModel.signOut()
            }
        }.launchIn(this)
    }

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEffect.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { effect ->
            when (effect) {
                is UserSettingsSideEffect.NavigateToAuthSelection -> navigateToAuthSelection()
                is UserSettingsSideEffect.ShowToast -> showToast(context, effect.message)
            }
        }.launchIn(this)
    }

    UserSettingsScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun UserSettingsScreen(
    uiState: UserSettingsUiState,
    onEvent: (UserSettingsUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            UserSettingsScreenTopBar(
                onNavigateToChat = { onEvent(UserSettingsUiEvent.OnNavigateToChat) },
            )
        }
    ) { innerPadding ->
        UserSettingsScreenContent(
            uiState = uiState,
            onEvent = onEvent,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}

@Composable
private fun UserSettingsScreenTopBar(
    onNavigateToChat: () -> Unit,
) {
    FirebaseSampleTopBar(
        title = {
            TitleLargeText(
                text = "ユーザ設定",
            )
        },
        navigationIcon = {
            FirebaseSampleIconButton(
                onClick = onNavigateToChat,
                icon = Icons.Rounded.ArrowBackIosNew,
            )
        },
    )
}
