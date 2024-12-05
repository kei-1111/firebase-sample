package com.example.firebasesample.ui.feature.sign_in

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
fun SignInScreen(
    navigateToChat: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    val latestNavigateToChat by rememberUpdatedState(navigateToChat)

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { event ->
            when (event) {
                is SignInUiEvent.OnEmailInputChange -> viewModel.updateEmail(event.email)
                is SignInUiEvent.OnPasswordInputChange -> viewModel.updatePassword(event.password)
                is SignInUiEvent.OnSignInButtonClick -> viewModel.submitLogin()
                is SignInUiEvent.OnGoogleSignInClick -> viewModel.startGoogleSignIn()
            }
        }.launchIn(this)
    }

    LaunchedEffect(lifecycleOwner, viewModel) {
        viewModel.uiEffect.flowWithLifecycle(lifecycleOwner.lifecycle).onEach { effect ->
            when (effect) {
                is SignInSideEffect.NavigateToChat -> latestNavigateToChat()
                is SignInSideEffect.ShowToast -> showToast(context, effect.message)
            }
        }.launchIn(this)
    }

    SignInScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun SignInScreen(
    uiState: SignInUiState,
    onEvent: (SignInUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        SignInScreenContent(
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