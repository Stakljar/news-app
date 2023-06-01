package agency.five.codebase.android.newsapp.data.repository

import agency.five.codebase.android.newsapp.data.database.PreferencesDao
import agency.five.codebase.android.newsapp.data.network.NewsService
import agency.five.codebase.android.newsapp.model.News
import agency.five.codebase.android.newsapp.model.Preference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class NewsRepositoryImpl(
    private val preferencesDao: PreferencesDao,
    private val newsService: NewsService,
    private val bgDispatcher: CoroutineDispatcher
) : NewsRepository {

    override fun getNews(
        keyword: String,
        categories: List<String>,
        countries: List<String>,
        languages: List<String>
    ): Flow<List<News>> =
        flow {
            emit(
                newsService.getNews(
                    keyword = keyword,
                    categories = categories,
                    countries = countries,
                    languages = languages
                )
            )
        }.map {
            it.data.map { item -> item.toNews() }
        }.flowOn(bgDispatcher)

    override fun deletePreference(preferenceId: Int) {
        preferencesDao.deletePreference(preferenceId)
    }

    override fun getDefaultPreference(): Flow<Preference?> =
        preferencesDao.getDefaultPreference().map { it?.toPreference() }

    override fun insertPreference(preference: Preference) {
        preferencesDao.insertPreference(preference.toPreferenceEntity())
    }

    override fun updatePreference(preference: Preference) {
        preferencesDao.updatePreference(preference.toPreferenceEntity())
    }

    override fun toggleDefaultPreference(preferenceId: Int) {
        preferencesDao.removeDefaultPreference()
        preferencesDao.addDefault(preferenceId)
    }

    val preferences = preferencesDao.getAll()
        .map {
            it.map {
                    preferenceEntity -> preferenceEntity.toPreference()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1
        )

    override fun getPreference(preferenceId: Int): Preference? =
        if (preferencesDao.getPreference(preferenceId) != null) {
            preferencesDao.getPreference(preferenceId).toPreference()
        } else {
            null
        }

    override fun getAllPreferences(): Flow<List<Preference>> = preferences
}
