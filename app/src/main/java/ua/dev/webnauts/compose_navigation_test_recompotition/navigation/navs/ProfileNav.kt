package ua.dev.webnauts.compose_navigation_test_recompotition.navigation.navs


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.NavRoutes
import ua.dev.webnauts.compose_navigation_test_recompotition.AppState
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.Graph
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.profile.ProfileScreen
import ua.dev.webnauts.compose_navigation_test_recompotition.utils.createTransitionComposableArg

/**
 * Рефакторинг
 * */
fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(Graph.Profile.graph, navOptions)
}
fun NavGraphBuilder.profileNav(appState: AppState) {
    navigation(
        route = Graph.Profile.graph,
        startDestination = NavRoutes.Profile.route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.Profile.route,
        ){
           ProfileScreen(appState = appState)
        }
    }
}