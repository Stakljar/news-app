package agency.five.codebase.android.newsapp.modules

import agency.five.codebase.android.newsapp.data.network.NewsService
import agency.five.codebase.android.newsapp.data.network.NewsServiceImpl
import agency.five.codebase.android.newsapp.data.network.mapper.UrlParamsMapper
import agency.five.codebase.android.newsapp.data.network.mapper.UrlParamsMapperImpl
import org.koin.dsl.module

val newsServiceModule = module {
    single<UrlParamsMapper> { UrlParamsMapperImpl() }
    single<NewsService> { NewsServiceImpl(client = get(), mapper = get()) }
}
