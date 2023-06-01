package agency.five.codebase.android.newsapp.modules.screens

import agency.five.codebase.android.newsapp.ui.screens.explore.ExploreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exploreModule = module {
    viewModel {
        ExploreViewModel(
            newsRepository = get(),
            preferencesMapper = get(),
            searchMapper = get()
        )
    }
}
