package ua.dev.webnauts.compose_navigation_test_recompotition.navigation.navs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.NavRoutes
import ua.dev.webnauts.compose_navigation_test_recompotition.AppState
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.Graph
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.home.HomeScreen
import ua.dev.webnauts.compose_navigation_test_recompotition.utils.createTransitionComposableArg



fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(Graph.Home.graph, navOptions)
}

fun NavGraphBuilder.homeNav(appState: AppState) {
    navigation(
        route = Graph.Home.graph,
        startDestination = NavRoutes.Home().route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.Home().route,
        ){
            HomeScreen(appState = appState)
        }
    }
}