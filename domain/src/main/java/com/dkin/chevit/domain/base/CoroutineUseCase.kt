package com.dkin.chevit.domain.base

import com.dkin.chevit.domain.base.Result.Failure
import com.dkin.chevit.domain.base.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<P, R : DomainModel>(private val dispatcher: CoroutineDispatcher) {

    protected abstract suspend fun execute(params: P): R

    suspend operator fun invoke(params: P): Result<R> = withContext(dispatcher) {
        try {
            Success(execute(params))
        } catch (e: Exception) {
            Failure(e)
        }
    }
}
