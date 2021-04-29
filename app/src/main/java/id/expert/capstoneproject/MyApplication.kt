package id.expert.capstoneproject

import android.app.Application
import id.expert.capstoneproject.core.di.databaseModule
import id.expert.capstoneproject.core.di.networkModule
import id.expert.capstoneproject.core.di.repositoryModule
import id.expert.capstoneproject.di.useCaseModule
import id.expert.capstoneproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}