package agency.five.codebase.android.newsapp.ui.screens.main

import agency.five.codebase.android.newsapp.R
import agency.five.codebase.android.newsapp.network.ConnectionTracker
import agency.five.codebase.android.newsapp.network.ConnectionTracker.registerConnection
import agency.five.codebase.android.newsapp.destination.Destination
import agency.five.codebase.android.newsapp.destination.PreferencesDestination
import agency.five.codebase.android.newsapp.notification.channel
import agency.five.codebase.android.newsapp.notification.hasPermissionForNotifications
import agency.five.codebase.android.newsapp.ui.screens.explore.ExploreRoute
import agency.five.codebase.android.newsapp.ui.screens.no_connection.NoConnectionRoute
import agency.five.codebase.android.newsapp.ui.screens.search.SearchRoute
import agency.five.codebase.android.newsapp.ui.screens.settings.SettingsRoute
import agency.five.codebase.android.newsapp.ui.theme.Blue
import agency.five.codebase.android.newsapp.ui.theme.spacing
import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            hasPermissionForNotifications.value = it
        }
    )
    val connectionState by ConnectionTracker.connectionState.collectAsState()
    registerConnection(context)
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationManager.createNotificationChannel(channel)
    }
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val isDrawerOpen = remember { derivedStateOf { scaffoldState.drawerState.isOpen } }
    if (connectionState) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar {
                    coroutineScope.launch { scaffoldState.drawerState.open() }
                }
            },
            drawerContent = {
                Drawer(
                    navController = navController,
                    scaffoldState = scaffoldState,
                    coroutineScope = coroutineScope
                )
            },
            drawerShape = RoundedCornerShape(
                topEnd = MaterialTheme.spacing.extraLarge,
                bottomEnd = MaterialTheme.spacing.extraLarge
            )
        ) { paddingValues ->
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Destination.ExploreDestination.route,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(Destination.ExploreDestination.route) {
                        BackHandler(enabled = isDrawerOpen.value) {
                            coroutineScope.launch { scaffoldState.drawerState.close() }
                        }
                        ExploreRoute(
                            onNavigateToSearch = {
                                navController.navigate(Destination.SearchDestination.route) {
                                    popUpTo(Destination.ExploreDestination.route)
                                    launchSingleTop = true
                                }
                            },
                            viewModel = getViewModel()
                        )
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    }
                    composable(Destination.SearchDestination.route) {
                        BackHandler(enabled = isDrawerOpen.value) {
                            coroutineScope.launch { scaffoldState.drawerState.close() }
                        }
                        SearchRoute(viewModel = getViewModel())
                    }
                    preferencesGraph(
                        navController = navController,
                        isDrawerOpen = isDrawerOpen,
                        scaffoldState = scaffoldState,
                        coroutineScope = coroutineScope
                    )
                    composable(Destination.SettingsDestination.route) {
                        BackHandler(enabled = isDrawerOpen.value) {
                            coroutineScope.launch { scaffoldState.drawerState.close() }
                        }
                        SettingsRoute(
                            modifier = Modifier.padding(MaterialTheme.spacing.sectionLarge),
                            viewModel = getViewModel()
                        )
                    }
                }
            }
        }
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NoConnectionRoute(modifier = Modifier.padding(MaterialTheme.spacing.sectionMedium))
        }
    }
}

@Composable
fun Drawer(
    navController: NavController,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = MaterialTheme.spacing.sectionSmall,
                vertical = MaterialTheme.spacing.sectionMedium
            ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sectionMedium)
    ) {
        Text(
            text = stringResource(id = R.string.home),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.clickable {
                navController.navigate(Destination.ExploreDestination.route) {
                    popUpTo(Destination.ExploreDestination.route)
                    launchSingleTop = true
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        )
        Text(
            text = stringResource(id = R.string.search),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.clickable {
                navController.navigate(Destination.SearchDestination.route) {
                    popUpTo(Destination.ExploreDestination.route)
                    launchSingleTop = true
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        )
        Text(
            text = stringResource(id = R.string.preferences),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.clickable {
                navController.navigate(PreferencesDestination.route) {
                    popUpTo(Destination.ExploreDestination.route)
                    launchSingleTop = true
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        )
        Text(
            text = stringResource(id = R.string.settings),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.clickable {
                navController.navigate(Destination.SettingsDestination.route) {
                    popUpTo(Destination.ExploreDestination.route)
                    launchSingleTop = true
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        )
    }
}

@Composable
fun TopBar(onClick: () -> Job) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.topbar_height))
            .background(Blue)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Menu",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = MaterialTheme.spacing.veryLarge)
                .clickable { onClick() },
            tint = Color.White
        )
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }
}
