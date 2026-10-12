package br.com.rodroid.mvi_core

/**
 * Base marker interface for all MVI Intents.
 * Each feature must define a sealed interface extending this.
 *
 * Example:
 * ```
 * sealed interface HomeIntent : MviIntent {
 *     data object LoadData : HomeIntent
 *     data class Search(val query: String) : HomeIntent
 * }
 * ```
 */
interface MviIntent
