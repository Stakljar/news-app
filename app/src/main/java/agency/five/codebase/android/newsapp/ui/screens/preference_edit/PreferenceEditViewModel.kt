package agency.five.codebase.android.newsapp.ui.screens.preference_edit

import agency.five.codebase.android.newsapp.data.repository.NewsRepository
import agency.five.codebase.android.newsapp.model.Preference
import agency.five.codebase.android.newsapp.ui.components.PreferenceCardViewState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PreferenceEditViewModel(
    private val newsRepository: NewsRepository,
    private val preferenceId: Int
) : ViewModel() {

    private val _preferenceViewState: MutableStateFlow<PreferenceCardViewState> = MutableStateFlow(
        PreferenceCardViewState(
            id = 0,
            keyword = "",
            categories = listOf(),
            countries = listOf(),
            languages = listOf(),
            isDefault = false
        )
    )

    val preferenceViewState: StateFlow<PreferenceCardViewState> = _preferenceViewState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val preference = newsRepository.getPreference(preferenceId = preferenceId)
            if (preference != null) {
                _preferenceViewState.value = PreferenceCardViewState(
                    id = preference.id,
                    keyword = preference.keyword,
                    categories = preference.categories,
                    countries = preference.countries,
                    languages = preference.languages,
                    isDefault = preference.isDefault
                )
            }
        }
    }

    var isError: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun enableError() {
        isError.value = true
    }

    fun disableError() {
        isError.value = false
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
            if (preferenceId == 0) {
                newsRepository.insertPreference(
                    Preference(
                        id = 0,
                        keyword = preferenceViewState.value.keyword,
                        categories = preferenceViewState.value.categories,
                        countries = preferenceViewState.value.countries,
                        languages = preferenceViewState.value.languages,
                        isDefault = false
                    )
                )
            } else {
                newsRepository.updatePreference(
                    Preference(
                        id = preferenceId,
                        keyword = preferenceViewState.value.keyword,
                        categories = preferenceViewState.value.categories,
                        countries = preferenceViewState.value.countries,
                        languages = preferenceViewState.value.languages,
                        isDefault = preferenceViewState.value.isDefault
                    )
                )
            }
        }
    }
}
