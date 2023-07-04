package com.dkin.chevit.presentation.auth

import com.dkin.chevit.core.mvi.ViewEffect
import com.dkin.chevit.core.mvi.ViewIntent
import com.dkin.chevit.core.mvi.ViewState

sealed interface AuthIntent : ViewIntent {
    object ClickedSignIn : AuthIntent
}

object AuthState : ViewState

sealed interface AuthEffect : ViewEffect {
    object NavigateToHome : AuthEffect
    object ShowSignInFailed : AuthEffect
}
