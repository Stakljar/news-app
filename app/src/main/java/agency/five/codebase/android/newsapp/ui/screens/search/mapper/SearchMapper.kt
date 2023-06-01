package agency.five.codebase.android.newsapp.ui.screens.search.mapper

import agency.five.codebase.android.newsapp.model.News
import agency.five.codebase.android.newsapp.ui.components.NewsCardViewState
import agency.five.codebase.android.newsapp.ui.screens.search.NewsViewState

interface SearchMapper {
    fun toNewsViewState(news: List<News>): NewsViewState
    fun toNewsCardViewState(news: News): NewsCardViewState
}
