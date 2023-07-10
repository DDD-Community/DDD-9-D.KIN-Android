package com.dkin.chevit.presentation.auth

import com.dkin.chevit.core.mvi.ViewEffect
import com.dkin.chevit.core.mvi.ViewIntent
import com.dkin.chevit.core.mvi.ViewState

sealed interface AuthIntent : ViewIntent {
    object SignInSuccess : AuthIntent

    @JvmInline
    value class SignInFailure(val throwable: Throwable) : AuthIntent
}

object AuthState : ViewState

sealed interface AuthEffect : ViewEffect {
    object NavigateToHome : AuthEffect
    object ShowSignInFailed : AuthEffect
}
