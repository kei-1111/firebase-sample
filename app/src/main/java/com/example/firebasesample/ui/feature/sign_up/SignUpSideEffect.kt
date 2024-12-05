package com.example.firebasesample.ui.feature.sign_up

import com.example.firebasesample.ui.base.SideEffect

interface SignUpSideEffect : SideEffect {
    data class ShowToast(val message: String) : SignUpSideEffect
    object NavigateToChat : SignUpSideEffect
}
