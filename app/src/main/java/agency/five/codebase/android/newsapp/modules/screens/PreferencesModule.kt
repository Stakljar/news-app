package agency.five.codebase.android.newsapp.modules.screens

import agency.five.codebase.android.newsapp.ui.screens.preferences.PreferencesViewModel
import agency.five.codebase.android.newsapp.ui.screens.preferences.mapper.PreferencesMapper
import agency.five.codebase.android.newsapp.ui.screens.preferences.mapper.PreferencesMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val preferencesModule = module {
    viewModel {
        PreferencesViewModel(
            newsRepository = get(),
            preferencesMapper = get()
        )
    }
    single<PreferencesMapper> { PreferencesMapperImpl() }
}
