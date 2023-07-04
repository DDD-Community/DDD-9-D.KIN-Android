package com.dkin.chevit.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {
    private val _jobs: MutableMap<String, Job> = mutableMapOf()

    private val _errorHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    /**
     * Coroutine Task 실행하며 감지되지 않은 예외케이스를 처리 합니다.
     *
     * @param exception 에러
     */
    protected open fun handleException(exception: Throwable) {
        Timber.e(exception)
    }

    /**
     * Coroutine launch 빌더를 통해 Job을 생성합니다.
     *
     * @param context       CoroutineContext
     * @param start         시작 정보
     * @param errorHandler  에러 핸들러
     * @param block         실행될 Task Block
     * @return Coroutine Job
     */
    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        errorHandler: CoroutineExceptionHandler = _errorHandler,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = (viewModelScope + errorHandler).launch(context, start, block)

    /**
     * Key 기준으로 Coroutine Job을 생성하고, 마지막 Job만 실행이 유지되도록 합니다.
     *
     * @param key           중복 실행을 방지하기 위한 Key
     * @param context       CoroutineContext
     * @param start         시작 정보
     * @param errorHandler  에러 핸들러
     * @param block         실행될 Task Block
     * @return
     */
    protected fun launchLatest(
        key: String,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        errorHandler: CoroutineExceptionHandler = _errorHandler,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = launch(context, start, errorHandler) {
        cancelLaunchLatestJob(key)
        launch(context, start, errorHandler, block).also {
            _jobs[key] = it
        }.join()
    }

    /**
     * ViewModel에서 관리하고 있는 Job Pool에서 Key를 기준으로 Job을 cancleAndJoin() 실행합니다.
     *
     * @param key   중복 실행을 방지하기 위한 Key
     * @return Key를 기준으로 Job을 취소요청후 취소 될때까지 기다립니다.
     */
    protected suspend fun cancelLaunchLatestJob(key: String) = _jobs[key]?.cancelAndJoin()

    /**
     * Coroutine async 빌더를 통해 Deferred<T>를 생성합니다.
     *
     * @param context       CoroutineContext
     * @param start         시작 정보
     * @param block         실행될 Task Block
     * @return Coroutine Job
     */
    protected fun <T> async(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T,
    ): Deferred<T> = viewModelScope.async(context, start, block)
}
