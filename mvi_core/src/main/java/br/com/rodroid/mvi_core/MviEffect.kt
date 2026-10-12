package br.com.rodroid.mvi_core

/**
 * Base marker interface for all MVI Effects (one-shot side effects).
 * Effects are consumed only once (e.g., navigation, snackbar, dialog).
 *
 * Example:
 * ```
 * sealed interface HomeEffect : MviEffect {
 *     data class ShowError(val message: String) : HomeEffect
 *     data object NavigateToDetail : HomeEffect
 * }
 * ```
 */
interface MviEffect
