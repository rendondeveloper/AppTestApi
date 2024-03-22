package com.rendonsoft.apptestapi.commos.module


import android.app.Application
import com.rendonsoft.apptestapi.feature.home.framework.di.homeModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class ApplicationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ApplicationApp)
            modules(instanceCommons, homeModule)
        }
    }
}