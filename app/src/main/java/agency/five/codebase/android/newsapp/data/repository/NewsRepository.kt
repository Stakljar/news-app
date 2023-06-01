package agency.five.codebase.android.newsapp.data.repository

import agency.five.codebase.android.newsapp.model.News
import agency.five.codebase.android.newsapp.model.Preference
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun deletePreference(preferenceId: Int)
    fun insertPreference(preference: Preference)
    fun updatePreference(preference: Preference)
    fun getAllPreferences(): Flow<List<Preference>>
    fun toggleDefaultPreference(preferenceId: Int)
    fun getPreference(preferenceId: Int): Preference?
    fun getNews(
        keyword: String,
        categories: List<String>,
        countries: List<String>,
        languages: List<String>
    ): Flow<List<News>>
    fun getDefaultPreference(): Flow<Preference?>
}
