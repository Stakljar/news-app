package agency.five.codebase.android.newsapp.modules.screens

import agency.five.codebase.android.newsapp.NewsApp
import agency.five.codebase.android.newsapp.ui.screens.settings.SettingsViewModel
import android.app.Application
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel {
        SettingsViewModel(application = get())
    }
    single<Application> { NewsApp.getApplication() }
}
