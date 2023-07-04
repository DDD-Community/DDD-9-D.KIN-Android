package com.dkin.chevit.presentation.home

import androidx.fragment.app.viewModels
import com.dkin.chevit.core.mvi.MVIFragment
import com.dkin.chevit.presentation.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : MVIFragment<FragmentHomeBinding, HomeIntent, HomeState, HomeEffect>(
    FragmentHomeBinding::inflate,
) {
    override val viewModel: HomeViewModel by viewModels()

    override fun processEffect(effect: HomeEffect) {
    }

    override fun processState(state: HomeState) {
    }
}
