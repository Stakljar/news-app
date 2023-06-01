package agency.five.codebase.android.newsapp.ui.screens.search.mapper

import agency.five.codebase.android.newsapp.model.News
import agency.five.codebase.android.newsapp.ui.components.NewsCardViewState
import agency.five.codebase.android.newsapp.ui.screens.search.NewsViewState

class SearchMapperImpl : SearchMapper {
    override fun toNewsViewState(news: List<News>): NewsViewState {
        return NewsViewState(
            newsCardViewStates = news.map {
                toNewsCardViewState(it).copy(country = fromUrlCountry(it.country), language = fromUrlLanguage(it.language))
            }
        )
    }

    override fun toNewsCardViewState(news: News): NewsCardViewState {
        return NewsCardViewState(
            title = news.title,
            author = news.author,
            country = news.country,
            language = news.language,
            publishedAt = news.publishedAt,
            imageUrl = news.imageUrl,
            description = news.description,
            source = news.source,
            sourceUrl = news.sourceUrl
        )
    }

    private fun fromUrlCountry(country: String): String =
        when (country) {
            "ar" -> "Argentina"
            "au" -> "Australia"
            "be" -> "Belgium"
            "at" -> "Austria"
            "br" -> "Brazil"
            "bg" -> "Bulgaria"
            "ca" -> "Canada"
            "cn" -> "China"
            "co" -> "Colombia"
            "cz" -> "Czech Republic"
            "eg" -> "Egypt"
            "fr" -> "France"
            "de" -> "Germany"
            "gr" -> "Greece"
            "hk" -> "Hong Kong"
            "hu" -> "Hungary"
            "in" -> "India"
            "id" -> "Indonesia"
            "ie" -> "Ireland"
            "il" -> "Israel"
            "it" -> "Italy"
            "jp" -> "Japan"
            "lv" -> "Latvia"
            "lt" -> "Lithuania"
            "my" -> "Malaysia"
            "mx" -> "Mexico"
            "ma" -> "Morocco"
            "nl" -> "Netherlands"
            "nz" -> "New Zealand"
            "ng" -> "Nigeria"
            "no" -> "Norway"
            "ph" -> "Philippines"
            "pl" -> "Poland"
            "pt" -> "Portugal"
            "ro" -> "Romania"
            "sa" -> "Saudi Arabia"
            "rs" -> "Serbia"
            "sg" -> "Singapore"
            "sk" -> "Slovakia"
            "si" -> "Slovenia"
            "za" -> "South Africa"
            "kr" -> "South Korea"
            "se" -> "Sweden"
            "tw" -> "Taiwan"
            "ch" -> "Switzerland"
            "th" -> "Thailand"
            "tr" -> "Turkey"
            "ae" -> "United Arab Emirates"
            "ua" -> "Ukraine"
            "gb" -> "United Kingdom"
            "us" -> "United States"
            else -> "Venezuela"
        }

    private fun fromUrlLanguage(language: String): String =
        when (language) {
            "ar" -> "Arabic"
            "zh" -> "Chinese"
            "nl" -> "Dutch"
            "en" -> "English"
            "fr" -> "French"
            "de" -> "German"
            "it" -> "Italian"
            "no" -> "Norwegian"
            "pt" -> "Portuguese"
            "ru" -> "Russian"
            "es" -> "Spanish"
            else -> "Swedish"
        }
}
