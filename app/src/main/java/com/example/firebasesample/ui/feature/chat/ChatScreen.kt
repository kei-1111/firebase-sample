package com.example.firebasesample.ui.feature.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.firebasesample.domain.model.TextMessage
import com.example.firebasesample.ui.component.FirebaseSampleIconButton
import com.example.firebasesample.ui.component.FirebaseSampleTopBar
import com.example.firebasesample.ui.component.TitleLargeText
import com.example.firebasesample.ui.utils.showToast
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Suppress("ModifierMissing")
@Composable
fun ChatScreen(
    navigateToUserSettings: () -> Unit,
    viewModel: ChatViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val messages by viewModel.messages.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val latestNavigateToUserSettings by rememberUpdatedState(navigateToUserSettings)

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { event ->
            when (event) {
                is ChatUiEvent.OnMessageInputChange -> viewModel.updateMessage(event.message)
                is ChatUiEvent.OnMessageSendClick -> viewModel.sendTextMessage()
                is ChatUiEvent.OnUserSettingsButtonClick -> {
                    focusManager.clearFocus()
                    latestNavigateToUserSettings()
                }
            }
        }.launchIn(this)
    }

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEffect.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { effect ->
            when (effect) {
                is ChatSideEffect.ShowToast -> showToast(context, effect.message)
            }
        }.launchIn(this)
    }

    ChatScreen(
        uiState = uiState,
        messages = messages.toPersistentList(),
        onEvent = viewModel::onEvent,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun ChatScreen(
    uiState: ChatUiState,
    messages: ImmutableList<TextMessage>,
    onEvent: (ChatUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ChatScreenTopBar(
                onUserSettingsButtonClick = { onEvent(ChatUiEvent.OnUserSettingsButtonClick) },
            )
        },
    ) { innerPadding ->
        ChatScreenContent(
            uiState = uiState,
            messages = messages,
            onEvent = onEvent,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()),
        )
    }
}

@Composable
private fun ChatScreenTopBar(
    onUserSettingsButtonClick: () -> Unit,
) {
    FirebaseSampleTopBar(
        title = {
            TitleLargeText(
                text = "チャット",
            )
        },
        actions = {
            FirebaseSampleIconButton(
                onClick = onUserSettingsButtonClick,
                icon = Icons.Rounded.Person,
            )
        },
    )
}
