package com.dkin.chevit.presentation.auth

import com.dkin.chevit.core.mvi.MVIViewModel
import com.dkin.chevit.domain.base.get
import com.dkin.chevit.domain.model.UserState
import com.dkin.chevit.domain.usecase.GetUserStateUseCase
import com.dkin.chevit.presentation.auth.AuthIntent.SignInFailure
import com.dkin.chevit.presentation.auth.AuthIntent.SignInSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getUserStateUseCase: GetUserStateUseCase,
) : MVIViewModel<AuthIntent, AuthState, AuthEffect>() {
    override fun createInitialState(): AuthState = AuthState

    override suspend fun processIntent(intent: AuthIntent) = when (intent) {
        SignInSuccess -> signIn()
        is SignInFailure -> showSignInFailed()
    }

    private suspend fun signIn() {
        val userState = getUserStateUseCase(Unit).get()
        if (userState is UserState.User) {
            navigateToHome()
        } else {
            showSignInFailed()
        }
    }

    private fun navigateToHome() {
        setEffect { AuthEffect.NavigateToHome }
    }

    private fun showSignInFailed() {
        setEffect { AuthEffect.ShowSignInFailed }
    }
}
