package agency.five.codebase.android.newsapp.data.network

import agency.five.codebase.android.newsapp.data.network.mapper.UrlParamsMapper
import agency.five.codebase.android.newsapp.data.network.model.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val BASE_URL = "http://api.mediastack.com/v1/news"
private const val API_KEY = "ee801628131adbccb05e6c549930e835"

class NewsServiceImpl(private val client: HttpClient, private val mapper: UrlParamsMapper) :
    NewsService {
    override suspend fun getNews(
        keyword: String,
        categories: List<String>,
        countries: List<String>,
        languages: List<String>
    ): NewsResponse {
        return client.get(
            urlString = "$BASE_URL?access_key=$API_KEY&keywords=$keyword&categories=" +
                    mapper.toUrlCategories(categories)
                        .joinToString(separator = ",") + "&countries=" +
                    mapper.toUrlCountries(countries).joinToString(separator = ",") + "&languages=" +
                    mapper.toUrlLanguages(languages).joinToString(separator = ",")
        ).body()
    }
}
