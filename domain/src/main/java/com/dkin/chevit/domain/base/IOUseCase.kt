package com.dkin.chevit.domain.base

abstract class IOUseCase<P, R : DomainModel>(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : CoroutineUseCase<P, R>(coroutineDispatcherProvider.dispatcherIO)
