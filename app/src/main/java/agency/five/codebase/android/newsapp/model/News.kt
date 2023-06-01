package agency.five.codebase.android.newsapp.model

data class News(
    val title: String,
    val author: String,
    val category: String,
    val description: String,
    val publishedAt: String,
    val imageUrl: String?,
    val source: String,
    val sourceUrl: String,
    val language: String,
    val country: String
)
