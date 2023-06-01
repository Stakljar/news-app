package agency.five.codebase.android.newsapp.modules

import agency.five.codebase.android.newsapp.data.repository.NewsRepository
import agency.five.codebase.android.newsapp.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val newsRepositoryModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl(
            preferencesDao = get(),
            newsService = get(),
            bgDispatcher = Dispatchers.IO
        )
    }
}
