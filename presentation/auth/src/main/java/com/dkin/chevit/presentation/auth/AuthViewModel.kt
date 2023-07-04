package com.dkin.chevit.presentation.auth

import com.dkin.chevit.core.mvi.MVIViewModel
import com.dkin.chevit.domain.base.get
import com.dkin.chevit.domain.model.UserState
import com.dkin.chevit.domain.usecase.SignInUseCase
import com.dkin.chevit.presentation.auth.AuthIntent.ClickedSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : MVIViewModel<AuthIntent, AuthState, AuthEffect>() {
    override fun createInitialState(): AuthState = AuthState

    override suspend fun processIntent(intent: AuthIntent) = when (intent) {
        ClickedSignIn -> signIn()
    }

    private suspend fun signIn() {
        val userState = signInUseCase(Unit).get()
        val effect = if (userState is UserState.User) {
            AuthEffect.NavigateToHome
        } else {
            AuthEffect.ShowSignInFailed
        }
        setEffect { effect }
    }
}
