package com.dkin.chevit.domain.usecase

import com.dkin.chevit.domain.base.CoroutineDispatcherProvider
import com.dkin.chevit.domain.base.IOUseCase
import com.dkin.chevit.domain.model.UserState
import com.dkin.chevit.domain.repository.AuthRepository

class GetUserStateUseCase(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val authRepository: AuthRepository,
) : IOUseCase<Unit, UserState>(coroutineDispatcherProvider = coroutineDispatcherProvider) {
    override suspend fun execute(params: Unit): UserState {
        return authRepository.getUserState()
    }
}
