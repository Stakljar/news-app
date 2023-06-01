package agency.five.codebase.android.newsapp.data.network.model

import agency.five.codebase.android.newsapp.model.News
import android.os.Build
import android.text.Html
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("data")
    val data: List<ApiNews>,
)

@Serializable
data class ApiNews(
    @SerialName("author")
    val author: String?,
    @SerialName("title")
    val title: String,
    @SerialName("country")
    val category: String,
    @SerialName("category")
    val country: String,
    @SerialName("language")
    val language: String,
    @SerialName("published_at")
    val publishedAt: String,
    @SerialName("image")
    val image: String?,
    @SerialName("description")
    val description: String,
    @SerialName("source")
    val source: String,
    @SerialName("url")
    val url: String
) {
    fun toNews(): News {
        return News(
            title = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                Html.fromHtml(
                    title,
                    Html.FROM_HTML_MODE_LEGACY
                ).toString()
            } else {
                Html.fromHtml(title).toString()
            },
            author = author ?: "Unknown",
            category = category,
            country = country,
            language = language,
            publishedAt = publishedAt,
            imageUrl = image,
            description = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                Html.fromHtml(
                    description,
                    Html.FROM_HTML_MODE_LEGACY
                ).toString()
            } else {
                Html.fromHtml(description).toString()
            },
            source = source,
            sourceUrl = url
        )
    }
}
