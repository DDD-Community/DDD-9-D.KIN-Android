package com.dkin.chevit.presentation.splash

import com.dkin.chevit.core.mvi.ViewEffect
import com.dkin.chevit.core.mvi.ViewIntent
import com.dkin.chevit.core.mvi.ViewState

sealed interface SplashIntent : ViewIntent {
    object CheckAuth : SplashIntent
}

object SplashState : ViewState

sealed interface SplashEffect : ViewEffect {
    object NavigateToAuth : SplashEffect
    object NavigateToHome : SplashEffect
}
