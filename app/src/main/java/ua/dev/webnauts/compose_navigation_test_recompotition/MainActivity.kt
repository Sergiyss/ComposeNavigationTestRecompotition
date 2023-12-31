package ua.dev.webnauts.compose_navigation_test_recompotition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.NavRoutes
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.Navigation
import ua.dev.webnauts.compose_navigation_test_recompotition.network.network_monitor.NetworkMonitor
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.home.components.navigationData
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.login.LoginViewModel
import ua.dev.webnauts.compose_navigation_test_recompotition.screen.welcome.Welcome
import ua.dev.webnauts.compose_navigation_test_recompotition.ui.theme.CleanProjectTheme
import ua.dev.webnauts.compose_navigation_test_recompotition.utils.animation.navanimation.enterTransitionHorizontally
import ua.dev.webnauts.compose_navigation_test_recompotition.utils.animation.navanimation.exitTransitionHorizontally
import javax.inject.Inject
/***
 *  Проект с одним модулем...
 *
 *  Для быстрого старта, где уже есть всё необходимое, чтобы начать программировать
 *  и накидывать дизайн, не отвлекаясь на подключение либ и настройки проекта, навигации
 * */


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel: LoginViewModel by viewModels()

        setContent {
            var startScreen  by remember { mutableStateOf<String?>( null ) }
            val animationState = remember { MutableTransitionState(false) }

            CleanProjectTheme {
                Welcome(route = { startRoute->
                    startScreen = startRoute
                    animationState.targetState = true
                })

                startScreen?.let { destination->
                    AnimatedVisibility(
                        visibleState = animationState,
                        enter = enterTransitionHorizontally(3000, 1000),
                        exit = exitTransitionHorizontally(3000, 1000)
                    ) {

                        rootScreen(
                            loginViewModel = loginViewModel,
                            networkMonitor = networkMonitor,
                            startDestination = destination
                        )
                    }
                }
            }
        }
    }
}


/***
 * Стартовая точка для [rootNavigation]
 * Здесь будут всё основные экраны. Отделил по той причине, что хочу [WelcomeScreen]
 * вынести из навигации вверх. Во многих моментах она будет только мешать.
 * */
@Composable
fun rootScreen(loginViewModel: LoginViewModel, networkMonitor: NetworkMonitor, startDestination : String){
    val appState  = rememberAppState(networkMonitor = networkMonitor,)
    var selectedItem by remember { mutableStateOf(0) }


    appState.showSnackBar("hi", duration = androidx.compose.material.SnackbarDuration.Short)


    val currentDestination =  appState.currentDestination


    Scaffold(
        bottomBar = {
            if (currentDestination?.route != NavRoutes.Welcome.route &&
                currentDestination?.route != NavRoutes.Login.route &&
                currentDestination?.route != NavRoutes.RegisterScreen().route) {
                NavigationBar {
                    navigationData.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                androidx.compose.material3.Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                )
                            },
                            label = { androidx.compose.material.Text(item.title) },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                appState.navigateToTopLevelDestination(item.route)
                            }
                        )
                    }
                }
            }
        }
    ) {
        Column(Modifier.padding(it)) {

            /**
             * @param startDestination - start destination for navigation
             * @param token - token for user,
             * @param startDestination start Screen
             * */
            Navigation(
                appState = appState,
                loginViewModel = loginViewModel,
                startDestination = startDestination
            )

        }
    }
}