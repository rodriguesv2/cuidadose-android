package br.com.rodroid.mvi_core

/**
 * Base marker interface for all MVI States.
 * Each feature must define a data class extending this.
 *
 * Example:
 * ```
 * data class HomeState(
 *     val isLoading: Boolean = false,
 *     val items: List<Item> = emptyList(),
 *     val error: String? = null
 * ) : MviState
 * ```
 */
interface MviState
