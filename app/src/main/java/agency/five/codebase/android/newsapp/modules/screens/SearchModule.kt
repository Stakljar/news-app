package agency.five.codebase.android.newsapp.modules.screens

import agency.five.codebase.android.newsapp.ui.screens.search.SearchViewModel
import agency.five.codebase.android.newsapp.ui.screens.search.mapper.SearchMapper
import agency.five.codebase.android.newsapp.ui.screens.search.mapper.SearchMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel {
        SearchViewModel(
            newsRepository = get(),
            searchMapper = get()
        )
    }
    single<SearchMapper> { SearchMapperImpl() }
}
