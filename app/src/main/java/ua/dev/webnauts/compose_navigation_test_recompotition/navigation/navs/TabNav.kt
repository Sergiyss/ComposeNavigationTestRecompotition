package ua.dev.webnauts.compose_navigation_test_recompotition.navigation.navs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.NavRoutes
import ua.dev.webnauts.compose_navigation_test_recompotition.AppState
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.Graph
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.tab_two.TabsTwoScreen
import ua.dev.webnauts.compose_navigation_test_recompotition.utils.createTransitionComposableArg

fun NavController.navigateToTab(navOptions: NavOptions? = null) {
    this.navigate(Graph.TabTwo.graph, navOptions)
}
fun NavGraphBuilder.tabNav(appState: AppState) {
    navigation(
        route = Graph.TabTwo.graph,
        startDestination = NavRoutes.TabTwo.route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.TabTwo.route,
        ){
            TabsTwoScreen(appState = appState)
        }
    }
}