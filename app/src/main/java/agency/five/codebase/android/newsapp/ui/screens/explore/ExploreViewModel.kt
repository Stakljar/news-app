package agency.five.codebase.android.newsapp.ui.screens.explore

import agency.five.codebase.android.newsapp.data.repository.NewsRepository
import agency.five.codebase.android.newsapp.ui.screens.preferences.mapper.PreferencesMapper
import agency.five.codebase.android.newsapp.ui.screens.search.mapper.SearchMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class ExploreViewModel(
    private val newsRepository: NewsRepository,
    preferencesMapper: PreferencesMapper,
    searchMapper: SearchMapper
) : ViewModel() {

    private val defaultPreferenceViewState = newsRepository.getDefaultPreference().map {
        if(it!= null) preferencesMapper.toPreferenceCardViewState(it) else null
    }.stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(1000L), initialValue = null)

    val newsViewState =
        defaultPreferenceViewState.flatMapLatest {
            if(it != null) {
                newsRepository.getNews(
                    keyword = it.keyword,
                    categories = it.categories,
                    countries = it.countries,
                    languages = it.languages
                )
            }
            else {
                flowOf(null)
            }
        }.map {
            if(it != null) searchMapper.toNewsViewState(it) else null
        }.stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(1000L), initialValue = null)
}
