package es.david.marvel

import android.app.Application
import es.david.marvel.di.charactersModule
import es.david.marvel.di.detailModule
import es.david.marvel.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    charactersModule,
                    detailModule,
                    networkModule
                )
            )
        }

    }
}