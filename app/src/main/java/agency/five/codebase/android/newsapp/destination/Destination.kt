package agency.five.codebase.android.newsapp.destination

const val EXPLORE_ROUTE = "explore"
const val SEARCH_ROUTE = "search"
const val PREFERENCES_ROUTE = "preferences"
const val SETTINGS_ROUTE = "settings"

sealed class Destination(val route: String) {
    object ExploreDestination : Destination(route = EXPLORE_ROUTE)
    object SearchDestination : Destination(route = SEARCH_ROUTE)
    object SettingsDestination : Destination(route = SETTINGS_ROUTE)
}
