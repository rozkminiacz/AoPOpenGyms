package dev.nowoczesny.aop.opengym

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application(){
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        startKoin {
            if (BuildConfig.DEBUG){
                androidLogger()
            }
            androidContext(this@App)
            modules(appModule)
        }
        super.onCreate()
    }
}