package agency.five.codebase.android.newsapp.destination

const val PREFERENCE_ROUTE = "preference"
const val PREFERENCE_PARAM = "prefId"
const val PREFERENCE_ROUTE_WITH_PARAM = "$PREFERENCE_ROUTE/{$PREFERENCE_PARAM}"

object PreferenceDestination : Destination(route = PREFERENCE_ROUTE_WITH_PARAM) {
    const val param = PREFERENCE_PARAM
    const val routeWithoutParam = PREFERENCE_ROUTE
    fun createNavigationRoute(preferenceId: Int): String {
        return "$PREFERENCE_ROUTE/$preferenceId"
    }
}
