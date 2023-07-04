package com.dkin.chevit.presentation.splash

import com.dkin.chevit.core.mvi.MVIViewModel
import com.dkin.chevit.domain.base.get
import com.dkin.chevit.domain.model.UserState.Guest
import com.dkin.chevit.domain.model.UserState.User
import com.dkin.chevit.domain.usecase.GetUserStateUseCase
import com.dkin.chevit.presentation.splash.SplashEffect.NavigateToAuth
import com.dkin.chevit.presentation.splash.SplashEffect.NavigateToHome
import com.dkin.chevit.presentation.splash.SplashIntent.CheckAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserStateUseCase: GetUserStateUseCase,
) : MVIViewModel<SplashIntent, SplashState, SplashEffect>() {
    override fun createInitialState(): SplashState = SplashState

    override suspend fun processIntent(intent: SplashIntent) = when (intent) {
        CheckAuth -> checkAuth()
    }

    private suspend fun checkAuth() {
        val effect = when (getUserStateUseCase(Unit).get()) {
            Guest -> NavigateToAuth
            is User -> NavigateToHome
        }
        setEffect { effect }
    }
}
