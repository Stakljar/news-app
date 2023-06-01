package agency.five.codebase.android.newsapp.ui.screens.preferences

import agency.five.codebase.android.newsapp.data.repository.NewsRepository
import agency.five.codebase.android.newsapp.ui.screens.preferences.mapper.PreferencesMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val newsRepository: NewsRepository,
    preferencesMapper: PreferencesMapper
) : ViewModel() {

    val preferences = newsRepository.getAllPreferences().map {
        preferencesMapper.toPreferencesViewState(preferences = it)
    }.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(1000L),
        initialValue = PreferencesViewState(emptyList())
    )

    fun deletePreference(preferenceId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deletePreference(preferenceId = preferenceId)
        }
    }

    fun toggleFavorite(preferenceId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.toggleDefaultPreference(preferenceId = preferenceId)
        }
    }
}
