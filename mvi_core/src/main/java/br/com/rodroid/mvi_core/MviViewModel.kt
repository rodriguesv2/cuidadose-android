package br.com.rodroid.mvi_core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Abstract base ViewModel for the MVI pattern.
 *
 * @param I MviIntent — user actions / events
 * @param S MviState — immutable UI state
 * @param E MviEffect — one-shot side effects (navigation, snackbar, etc.)
 *
 * Usage:
 * ```
 * class HomeViewModel : MviViewModel<HomeIntent, HomeState, HomeEffect>(HomeState()) {
 *
 *     override fun handleIntent(intent: HomeIntent) {
 *         when (intent) {
 *             is HomeIntent.LoadData -> loadData()
 *         }
 *     }
 *
 *     private fun loadData() {
 *         setState { copy(isLoading = true) }
 *         viewModelScope.launch {
 *             // ...
 *             sendEffect(HomeEffect.ShowError("Ops!"))
 *         }
 *     }
 * }
 * ```
 */
abstract class MviViewModel<I : MviIntent, S : MviState, E : MviEffect>(
    initialState: S
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect = Channel<E>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    /**
     * Dispatches an intent to be processed by the ViewModel.
     */
    fun dispatch(intent: I) {
        handleIntent(intent)
    }

    /**
     * Override to handle each intent and produce new states / effects.
     */
    protected abstract fun handleIntent(intent: I)

    /**
     * Updates the current state by applying a transformation.
     */
    protected fun setState(reducer: S.() -> S) {
        _state.value = _state.value.reducer()
    }

    /**
     * Emits a one-shot side effect.
     */
    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}
