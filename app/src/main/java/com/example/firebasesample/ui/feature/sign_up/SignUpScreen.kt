package com.example.firebasesample.ui.feature.sign_up

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
fun SignUpScreen(
    navigateToChat: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    val latestNavigateToChat by rememberUpdatedState(navigateToChat)

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { event ->
            when (event) {
                is SignUpUiEvent.OnEmailInputChange -> viewModel.updateEmail(event.email)
                is SignUpUiEvent.OnPasswordInputChange -> viewModel.updatePassword(event.password)
                is SignUpUiEvent.OnSignUpButtonClick -> viewModel.submitRegister()
                is SignUpUiEvent.OnGoogleSignInClick -> viewModel.startGoogleSignIn()
            }
        }.launchIn(this)
    }

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEffect.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { effect ->
            when (effect) {
                is SignUpSideEffect.NavigateToChat -> latestNavigateToChat()
                is SignUpSideEffect.ShowToast -> showToast(context, effect.message)
            }
        }.launchIn(this)
    }

    SignUpScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun SignUpScreen(
    uiState: SignUpUiState,
    onEvent: (SignUpUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        SignUpScreenContent(
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
