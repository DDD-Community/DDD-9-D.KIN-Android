package com.dkin.chevit.presentation.auth

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.dkin.chevit.core.mvi.MVIFragment
import com.dkin.chevit.presentation.auth.AuthEffect.NavigateToHome
import com.dkin.chevit.presentation.auth.AuthEffect.ShowSignInFailed
import com.dkin.chevit.presentation.auth.databinding.FragmentAuthBinding
import com.dkin.chevit.presentation.deeplink.DeepLink.Home
import com.dkin.chevit.presentation.deeplink.deepLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Auth : MVIFragment<FragmentAuthBinding, AuthIntent, AuthState, AuthEffect>(
    FragmentAuthBinding::inflate,
) {
    override val viewModel: AuthViewModel by viewModels()

    override fun initView() = binding {
        btnSignIn.setOnClickListener {
            setIntent(AuthIntent.ClickedSignIn)
        }
    }

    override fun processState(state: AuthState) {
    }

    override fun processEffect(effect: AuthEffect) = when (effect) {
        NavigateToHome -> deepLink(Home) {
            popUpTo(R.id.auth) { inclusive = true }
        }

        ShowSignInFailed -> Toast.makeText(
            requireContext(),
            "Sign in failed",
            Toast.LENGTH_SHORT,
        ).show()
    }
}
