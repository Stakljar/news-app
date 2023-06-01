package agency.five.codebase.android.newsapp.data.network

import agency.five.codebase.android.newsapp.data.network.model.NewsResponse

interface NewsService {
    suspend fun getNews(
        keyword: String,
        categories: List<String>,
        countries: List<String>,
        languages: List<String>
    ): NewsResponse
}
