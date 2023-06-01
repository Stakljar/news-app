package agency.five.codebase.android.newsapp.ui.screens.main

import agency.five.codebase.android.newsapp.destination.PreferenceDestination
import agency.five.codebase.android.newsapp.destination.PreferencesDestination
import agency.five.codebase.android.newsapp.ui.screens.preference_edit.PreferenceEditRoute
import agency.five.codebase.android.newsapp.ui.screens.preferences.PreferencesRoute
import androidx.activity.compose.BackHandler
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.State
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.preferencesGraph(
    navController: NavController,
    isDrawerOpen: State<Boolean>,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {
    navigation(
        startDestination = PreferencesDestination.alt,
        route = PreferencesDestination.route
    ) {
        composable(PreferencesDestination.alt) {
            BackHandler(enabled = isDrawerOpen.value) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
            PreferencesRoute(
                onNavigateToPreferenceEdit = { id ->
                    navController.navigate(
                        PreferenceDestination.createNavigationRoute(id)
                    )
                },
                viewModel = getViewModel()
            )
        }
        composable(
            route = PreferenceDestination.route,
            arguments = listOf(navArgument(PreferenceDestination.param) { type = NavType.IntType })
        ) {
            BackHandler(enabled = isDrawerOpen.value) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
            PreferenceEditRoute(
                viewModel = getViewModel {
                    parametersOf(
                        it.arguments?.getInt(
                            PreferenceDestination.param
                        ) ?: throw IllegalArgumentException("No id found.")
                    )
                },
                onNavigateToPreferences = navController::popBackStack
            )
        }
    }
}
