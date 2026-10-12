package br.com.rodroid.cuidadose.di

import org.koin.dsl.module

/**
 * Root Koin module for the app.
 * Add your feature modules here using `includes(featureModule)`.
 *
 * Example:
 * ```
 * val appModule = module {
 *     includes(homeModule, profileModule)
 * }
 * ```
 */
val appModule = module {
    includes(databaseModule)
}

