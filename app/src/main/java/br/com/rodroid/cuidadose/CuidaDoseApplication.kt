package br.com.rodroid.cuidadose

import android.app.Application
import br.com.rodroid.cuidadose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CuidaDoseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@CuidaDoseApplication)
            modules(appModule)
        }
    }
}
