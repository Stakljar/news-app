package agency.five.codebase.android.newsapp.ui.screens.search

import agency.five.codebase.android.newsapp.data.repository.NewsRepository
import agency.five.codebase.android.newsapp.model.Preference
import agency.five.codebase.android.newsapp.params.Params
import agency.five.codebase.android.newsapp.ui.components.PreferenceCardViewState
import agency.five.codebase.android.newsapp.ui.screens.search.mapper.SearchMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val newsRepository: NewsRepository,
    private val searchMapper: SearchMapper
) : ViewModel() {

    private val _preferenceViewState: MutableStateFlow<PreferenceCardViewState> = MutableStateFlow(
        PreferenceCardViewState(
            id = 0,
            keyword = "",
            categories = Params.getCategories(),
            countries = Params.getCountries(),
            languages = Params.getLanguages(),
            isDefault = false
        )
    )

    val preferenceViewState: StateFlow<PreferenceCardViewState> = _preferenceViewState.asStateFlow()

    private val _preferenceSavedViewState = MutableStateFlow(
        PreferenceCardViewState(
            id = 0,
            keyword = "",
            categories = Params.getCategories(),
            countries = Params.getCountries(),
            languages = Params.getLanguages(),
            isDefault = false
        )
    )

    private val preferenceSavedViewState: StateFlow<PreferenceCardViewState> =
        _preferenceSavedViewState.asStateFlow()

    val newsViewState: StateFlow<NewsViewState> = preferenceSavedViewState.flatMapLatest {
        newsRepository.getNews(
            keyword = it.keyword,
            categories = it.categories,
            countries = it.countries,
            languages = it.languages
        )
    }.map {
        searchMapper.toNewsViewState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = NewsViewState(emptyList())
    )

    val isSearchEmpty: StateFlow<Boolean> = newsViewState.map {
        it.newsCardViewStates.isEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = false
    )

    var isFilterEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)

    var isAddPreferenceButtonShown: MutableStateFlow<Boolean> = MutableStateFlow(false)

    var areCategoriesExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)

    var areCountriesExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)

    var areLanguagesExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun toggleCategories() {
        areCategoriesExpanded.value = !areCategoriesExpanded.value
    }

    fun toggleCountries() {
        areCountriesExpanded.value = !areCountriesExpanded.value
    }

    fun toggleLanguages() {
        areLanguagesExpanded.value = !areLanguagesExpanded.value
    }

    fun enableFilter() {
        isFilterEnabled.value = true
    }

    fun enableAddPreferenceButton() {
        isAddPreferenceButtonShown.value = true
    }

    fun disableAddPreferenceButton() {
        isAddPreferenceButtonShown.value = false
    }

    fun saveSearchedState() {
        _preferenceSavedViewState.value = preferenceViewState.value
    }

    fun changeKeywordValue(keyword: String) {
        _preferenceViewState.value = _preferenceViewState.value.copy(keyword = keyword)
    }

    fun insertCategory(category: String) {
        _preferenceViewState.value =
            _preferenceViewState.value.copy(categories = (_preferenceViewState.value.categories + category).distinct())
    }

    fun removeCategory(category: String) {
        _preferenceViewState.value =
            _preferenceViewState.value.copy(categories = (_preferenceViewState.value.categories - category).distinct())
    }

    fun insertCountry(country: String) {
        _preferenceViewState.value =
            _preferenceViewState.value.copy(countries = (_preferenceViewState.value.countries + country).distinct())
    }

    fun removeCountry(country: String) {
        _preferenceViewState.value =
            _preferenceViewState.value.copy(countries = (_preferenceViewState.value.countries - country).distinct())
    }

    fun insertLanguage(language: String) {
        _preferenceViewState.value =
            _preferenceViewState.value.copy(languages = (_preferenceViewState.value.languages + language).distinct())
    }

    fun removeLanguage(language: String) {
        _preferenceViewState.value =
            _preferenceViewState.value.copy(languages = (_preferenceViewState.value.languages - language).distinct())
    }

    fun insertPreference() {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.insertPreference(
                Preference(
                    id = 0,
                    keyword = preferenceSavedViewState.value.keyword,
                    categories = preferenceSavedViewState.value.categories,
                    countries = preferenceSavedViewState.value.countries,
                    languages = preferenceSavedViewState.value.languages,
                    isDefault = preferenceSavedViewState.value.isDefault
                )
            )
        }
    }
}
