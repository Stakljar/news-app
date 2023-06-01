package agency.five.codebase.android.newsapp.mock

import agency.five.codebase.android.newsapp.ui.components.NewsCardViewState
import agency.five.codebase.android.newsapp.ui.components.PreferenceCardViewState

object Mock {
    fun getNews(): NewsCardViewState = NewsCardViewState(
        title = "Default title",
        author = "Default author",
        description = "Default description default description default description default description default description",
        publishedAt = "2018-01-02T08:20:10+00:00",
        imageUrl = null,
        source = "Default source",
        sourceUrl = "",
        language = "en",
        country = "us"
    )

    fun getNewsList(): List<NewsCardViewState> = listOf(
        NewsCardViewState(
            title = "Default title",
            author = "Default author",
            description = "Default description default description default description default description default description",
            publishedAt = "2018-01-02T08:20:10+00:00",
            imageUrl = null,
            source = "Default source",
            sourceUrl = "",
            language = "en",
            country = "us"
        ),
        NewsCardViewState(
            title = "Default title",
            author = "Default author",
            description = "Default description default description default description default description default description",
            publishedAt = "2018-01-02T08:20:10+00:00",
            imageUrl = null,
            source = "Default source",
            sourceUrl = "",
            language = "en",
            country = "us"
        ),
        NewsCardViewState(
            title = "Default title",
            author = "TMZ.com",
            description = "Default description default description default description default description default description",
            publishedAt = "2018-01-02T08:20:10+00:00",
            imageUrl = null,
            source = "Default source",
            sourceUrl = "https://www.tmz.com",
            language = "en",
            country = "us"
        )
    )

    fun getPref(): PreferenceCardViewState = PreferenceCardViewState(
        id = 1,
        keyword = "Default keyword",
        categories = listOf("General", "Business"),
        countries = listOf("United States"),
        languages = listOf("English"),
        isDefault = false
    )

    fun getPrefList(): List<PreferenceCardViewState> = listOf(
        PreferenceCardViewState(
            id = 2,
            keyword = "Default keyword",
            categories = listOf("General", "Business"),
            countries = listOf("United States"),
            languages = listOf("English"),
            isDefault = false
        ),
        PreferenceCardViewState(
            id = 3,
            keyword = "Default keyword",
            categories = listOf("General", "Business"),
            countries = listOf("United States"),
            languages = listOf("English"),
            isDefault = false
        ),
        PreferenceCardViewState(
            id = 4,
            keyword = "Default keyword",
            categories = listOf("General", "Business"),
            countries = listOf("United States"),
            languages = listOf("English"),
            isDefault = false
        )
    )
}
