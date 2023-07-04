package com.dkin.chevit.presentation.home

import com.dkin.chevit.core.mvi.ViewEffect
import com.dkin.chevit.core.mvi.ViewIntent
import com.dkin.chevit.core.mvi.ViewState

sealed interface HomeIntent : ViewIntent

object HomeState : ViewState

sealed interface HomeEffect : ViewEffect
