package com.dkin.chevit.domain.base

/**
 * @param doOnComplete 성공여부 상관 없이 onOnError, doOnSuccess 호출 후 맨 마지막으로 실행 됩니다.
 * @param doOnError 실패시 실행 됩니다.
 * @param doOnSuccess 성공시 실행 됩니다.
 *
 * Result 값을 기준으로 doOnError, doOnSuccess, doOnComplete 호출 됩니다.
 * Result 값이 성공인 경우 doOnSuccess -> doOnComplete 순서대로 호출 됩니다.
 * Result 값이 실패인 경우 doOnError -> doOnComplete 순서대로 호출 됩니다.
 */
fun <T : DomainModel> Result<T>.onComplete(
    doOnComplete: () -> Unit = {},
    doOnError: (exception: Throwable) -> Unit = { throw it },
    doOnSuccess: T.() -> Unit,
) {
    when (this) {
        is Result.Failure -> doOnError(exception)
        is Result.Success -> doOnSuccess(data)
    }
    doOnComplete()
}

/**
 * @param doOnComplete 성공여부 상관 없이 onOnError, doOnSuccess 호출 후 맨 마지막으로 실행 됩니다.
 * @param doOnError 실패시 실행 됩니다.
 * @param doOnSuccess 성공시 실행 됩니다.
 *
 * Result 값을 기준으로 doOnError, doOnSuccess, doOnComplete 호출 됩니다.
 * Result 값이 성공인 경우 doOnSuccess -> doOnComplete 순서대로 호출 됩니다.
 * Result 값이 실패인 경우 doOnError -> doOnComplete 순서대로 호출 됩니다.
 */
suspend fun <T : DomainModel> Result<T>.onCompleteSuspend(
    doOnComplete: suspend () -> Unit = {},
    doOnError: suspend (exception: Throwable) -> Unit = { throw it },
    doOnSuccess: suspend T.() -> Unit,
) {
    when (this) {
        is Result.Failure -> doOnError(exception)
        is Result.Success -> doOnSuccess(data)
    }
    doOnComplete()
}

inline fun <reified T : DomainModel> Result<T>.get(): T = when (this) {
    is Result.Success -> data
    is Result.Failure -> throw exception
}

inline fun <reified T : DomainModel> Result<T>.getOrNull(): T? = when (this) {
    is Result.Success -> data
    is Result.Failure -> null
}
