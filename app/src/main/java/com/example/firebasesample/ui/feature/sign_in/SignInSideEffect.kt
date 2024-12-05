package com.example.firebasesample.ui.feature.sign_in

import com.example.firebasesample.ui.base.SideEffect

interface SignInSideEffect : SideEffect {
    data class ShowToast(val message: String) : SignInSideEffect
    object NavigateToChat : SignInSideEffect
}
