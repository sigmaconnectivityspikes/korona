package se.sigmaconnectivity.korona

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import se.sigmaconnectivity.korona.data.dataModule
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(appModule, dataModule)
        }
    }

}