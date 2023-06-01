package agency.five.codebase.android.newsapp.data.network.mapper

interface UrlParamsMapper {
    fun toUrlCategories(categories: List<String>): List<String>
    fun toUrlCountries(countries: List<String>): List<String>
    fun toUrlLanguages(languages: List<String>): List<String>
}
