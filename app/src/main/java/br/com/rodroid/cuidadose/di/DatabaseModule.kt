package br.com.rodroid.cuidadose.di

import androidx.room.Room
import br.com.rodroid.cuidadose.data.local.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "cuidadose.db"
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    // DAOs
    single { get<AppDatabase>().medicationDao() }
}
