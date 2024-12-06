package com.example.firebasesample.ui.feature.auth_selection

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun AuthSelectionScreen(
    navigateToSignUp: () -> Unit,
    navigateToSignIn: () -> Unit,
    viewModel: AuthSelectionViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val latestNavigateToSignIn by rememberUpdatedState(navigateToSignIn)
    val latestNavigateToSignUp by rememberUpdatedState(navigateToSignUp)

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { event ->
            when (event) {
                is AuthSelectionUiEvent.OnSignInButtonClick -> latestNavigateToSignIn()
                is AuthSelectionUiEvent.OnSignUpButtonClick -> latestNavigateToSignUp()
            }
        }.launchIn(this)
    }

    AuthSelectionScreen(
        onEvent = viewModel::onEvent,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun AuthSelectionScreen(
    onEvent: (AuthSelectionUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        AuthSelectionScreenContent(
            onEvent = onEvent,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}
