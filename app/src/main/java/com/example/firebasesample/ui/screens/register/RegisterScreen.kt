package com.example.firebasesample.ui.screens.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.firebasesample.ui.component.FirebaseSampleLoading
import com.example.firebasesample.ui.utils.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Suppress("ModifierMissing")
@Composable
fun RegisterScreen(
    navigateToChat: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    val latestNavigateToChat by rememberUpdatedState(navigateToChat)

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { event ->
            when (event) {
                is RegisterUiEvent.OnEmailInputChange -> viewModel.updateEmail(event.email)
                is RegisterUiEvent.OnPasswordInputChange -> viewModel.updatePassword(event.password)
                is RegisterUiEvent.OnRegisterButtonClick -> viewModel.submitRegister()
                is RegisterUiEvent.OnGoogleSignInClick -> viewModel.startGoogleSignIn()
            }
        }.launchIn(this)
    }

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEffect.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { effect ->
            when (effect) {
                is RegisterUiEffect.NavigateToChat -> latestNavigateToChat()
                is RegisterUiEffect.ShowToast -> showToast(context, effect.message)
            }
        }.launchIn(this)
    }

    RegisterScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun RegisterScreen(
    uiState: RegisterUiState,
    onEvent: (RegisterUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        RegisterScreenContent(
            uiState = uiState,
            onEvent = onEvent,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )

        AnimatedVisibility(uiState.isLoading) {
            FirebaseSampleLoading()
        }
    }
}
