package agency.five.codebase.android.newsapp.params

import okhttp3.internal.immutableListOf

object Params {

    private val categories = immutableListOf("General", "Business", "Entertainment", "Health", "Science", "Sports", "Technology")
    fun getCategories(): List<String> = categories

    private val languages = immutableListOf(
        "Arabic",
        "Chinese",
        "Dutch",
        "English",
        "French",
        "German",
        "Italian",
        "Norwegian",
        "Portuguese",
        "Russian",
        "Spanish",
        "Swedish"
    )
    fun getLanguages(): List<String> = languages

    private val countries = immutableListOf(
        "Argentina",
        "Australia",
        "Austria",
        "Belgium",
        "Brazil",
        "Bulgaria",
        "Canada",
        "China",
        "Colombia",
        "Czech Republic",
        "Egypt",
        "France",
        "Germany",
        "Greece",
        "Hong Kong",
        "Hungary",
        "India",
        "Indonesia",
        "Ireland",
        "Israel",
        "Italy",
        "Japan",
        "Latvia",
        "Lithuania",
        "Malaysia",
        "Mexico",
        "Morocco",
        "Netherlands",
        "New Zealand",
        "Nigeria",
        "Norway",
        "Philippines",
        "Poland",
        "Portugal",
        "Romania",
        "Saudi Arabia",
        "Serbia",
        "Singapore",
        "Slovakia",
        "Slovenia",
        "South Africa",
        "South Korea",
        "Sweden",
        "Switzerland",
        "Taiwan",
        "Thailand",
        "Turkey",
        "UAE",
        "Ukraine",
        "United Kingdom",
        "United States",
        "Venezuela"
    )
    fun getCountries(): List<String> = countries
}
