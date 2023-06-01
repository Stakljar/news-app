package agency.five.codebase.android.newsapp.model

import agency.five.codebase.android.newsapp.data.database.PreferenceEnt

data class Preference(
    val id: Int,
    val keyword: String,
    val categories: List<String>,
    val countries: List<String>,
    val languages: List<String>,
    val isDefault: Boolean
) {
    fun toPreferenceEntity(): PreferenceEnt{
        return PreferenceEnt(
            id = id,
            keyword = keyword,
            categories = categories,
            countries = countries,
            languages = languages,
            isDefault = isDefault
        )
    }
}
