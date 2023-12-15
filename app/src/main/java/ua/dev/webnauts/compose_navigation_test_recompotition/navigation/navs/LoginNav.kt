package ua.dev.webnauts.compose_navigation_test_recompotition.navigation.navs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.NavRoutes
import ua.dev.webnauts.compose_navigation_test_recompotition.AppState
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.Graph
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.login.LoginScreen
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.login.LoginViewModel
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.login.RegistrationScreen
import ua.dev.webnauts.compose_navigation_test_recompotition.utils.createTransitionComposableArg

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginNav(appState: AppState, loginViewModel: LoginViewModel) {
    navigation(
        route = Graph.Login.graph,
        startDestination = NavRoutes.Login.route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.Login.route,
        ){

            LoginScreen(appState = appState, loginViewModel)
        }

        createTransitionComposableArg(
            route = NavRoutes.RegisterScreen().route,
        ){

            RegistrationScreen(appState = appState, loginViewModel)
        }
    }
}