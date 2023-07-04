package com.dkin.chevit.core.mvi

import com.dkin.chevit.core.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class MVIViewModel<I : ViewIntent, S : ViewState, E : ViewEffect> : BaseViewModel() {

    abstract fun createInitialState(): S

    private val initialState: S by lazy { createInitialState() }

    private val _intent: MutableSharedFlow<I> = MutableSharedFlow()
    private val intent = _intent.asSharedFlow()

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect: Channel<E> = Channel()
    val effect = _effect.receiveAsFlow()

    private val _currentState
        get() = state.value

    init {
        subscribeIntent()
    }

    private fun subscribeIntent() {
        launch {
            intent.collect {
                try {
                    processIntent(it)
                } catch (exception: Throwable) {
                    handleException(exception)
                }
            }
        }
    }

    /**
     * Intent를 처리 합니다.
     */
    abstract suspend fun processIntent(intent: I)

    /**
     * 새로운 Intent를 방출합니다.
     */
    fun dispatch(intent: I) {
        val newIntent = intent
        launch { _intent.emit(newIntent) }
    }

    protected fun <T> currentState(action: S.() -> T): T {
        return _currentState.action()
    }

    /**
     * UI 상태를 업데이트 합니다.
     */
    protected fun setState(reduce: S.() -> S) {
        val newState = _currentState.reduce()
        _state.value = newState
    }

    /**
     * View Effect를 발생합니다.
     */
    protected fun setEffect(builder: () -> E) {
        val newEffect = builder()
        launch { _effect.send(newEffect) }
    }
}
