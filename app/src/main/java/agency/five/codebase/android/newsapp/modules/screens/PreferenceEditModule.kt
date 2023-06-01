package agency.five.codebase.android.newsapp.modules.screens

import agency.five.codebase.android.newsapp.ui.screens.preference_edit.PreferenceEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val preferenceEditModule = module {
    viewModel { id ->
        PreferenceEditViewModel(
            newsRepository = get(),
            preferenceId = id.get()
        )
    }
}
