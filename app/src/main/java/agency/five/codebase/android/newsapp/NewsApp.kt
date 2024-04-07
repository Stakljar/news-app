package agency.five.codebase.android.newsapp

import agency.five.codebase.android.newsapp.modules.*
import agency.five.codebase.android.newsapp.modules.screens.*
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@NewsApp)
            modules(
                networkModule,
                newsRepositoryModule,
                databaseModule,
                preferenceEditModule,
                preferencesModule,
                searchModule,
                newsServiceModule,
                exploreModule,
                settingsModule
            )
        }
    }

    companion object {
        private lateinit var instance: NewsApp

        fun getApplication(): NewsApp {
            return instance
        }
    }
}
