package com.dkin.chevit.presentation.splash

import androidx.fragment.app.viewModels
import com.dkin.chevit.core.mvi.MVIFragment
import com.dkin.chevit.presentation.deeplink.DeepLink
import com.dkin.chevit.presentation.deeplink.deepLink
import com.dkin.chevit.presentation.splash.SplashEffect.NavigateToAuth
import com.dkin.chevit.presentation.splash.SplashEffect.NavigateToHome
import com.dkin.chevit.presentation.splash.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Splash : MVIFragment<FragmentSplashBinding, SplashIntent, SplashState, SplashEffect>(
    FragmentSplashBinding::inflate,
) {
    override val viewModel: SplashViewModel by viewModels()

    override fun initView() {
        setIntent(SplashIntent.CheckAuth)
    }

    override fun processState(state: SplashState) {
    }

    override fun processEffect(effect: SplashEffect) = when (effect) {
        NavigateToAuth -> deepLink(DeepLink.Auth) {
            popUpTo(R.id.splash) { inclusive = true }
        }
        NavigateToHome -> deepLink(DeepLink.Home) {
            popUpTo(R.id.splash) { inclusive = true }
        }
    }
}
