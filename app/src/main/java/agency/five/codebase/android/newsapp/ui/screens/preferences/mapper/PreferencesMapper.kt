package agency.five.codebase.android.newsapp.ui.screens.preferences.mapper

import agency.five.codebase.android.newsapp.model.Preference
import agency.five.codebase.android.newsapp.ui.components.PreferenceCardViewState
import agency.five.codebase.android.newsapp.ui.screens.preferences.PreferencesViewState

interface PreferencesMapper {
    fun toPreferencesViewState(preferences: List<Preference>): PreferencesViewState
    fun toPreferenceCardViewState(preference: Preference): PreferenceCardViewState
}
