package agency.five.codebase.android.newsapp.ui.screens.preferences.mapper

import agency.five.codebase.android.newsapp.model.Preference
import agency.five.codebase.android.newsapp.ui.components.PreferenceCardViewState
import agency.five.codebase.android.newsapp.ui.screens.preferences.PreferencesViewState

class PreferencesMapperImpl : PreferencesMapper {
    override fun toPreferencesViewState(preferences: List<Preference>): PreferencesViewState =
        PreferencesViewState(
            preferences = preferences.map {
                toPreferenceCardViewState(it)
            }
        )

    override fun toPreferenceCardViewState(preference: Preference): PreferenceCardViewState {
        return PreferenceCardViewState(
            id = preference.id,
            keyword = preference.keyword,
            categories = preference.categories,
            countries = preference.countries,
            languages = preference.languages,
            isDefault = preference.isDefault
        )
    }
}
