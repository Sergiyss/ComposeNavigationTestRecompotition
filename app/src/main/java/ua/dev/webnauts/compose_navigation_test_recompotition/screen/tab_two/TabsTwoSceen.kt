package ua.dev.webnauts.compose_navigation_test_recompotition.screen.tab_two

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import ua.dev.webnauts.compose_navigation_test_recompotition.AppState
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.NavRoutes
import ua.dev.webnauts.compose_navigation_test_recompotition.state.collectWithLifecycle
import ua.dev.webnauts.compose_navigation_test_recompotition.ui.compose_ui.top_bars.DefaultTopBar
import ua.dev.webnauts.compose_navigation_test_recompotition.ui.theme.spacing
import ua.dev.webnauts.compose_navigation_test_recompotition.utils.navigateSingleTopTo

@Composable
fun TabsTwoScreen(
    appState: AppState,
    tabsTwoViewModel: TabsTwoViewModel = hiltViewModel(),
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current
) {
    var tst  by remember { mutableStateOf( "" ) }
    val uiState by tabsTwoViewModel.collectWithLifecycle()
    val emailCharacterCount by tabsTwoViewModel.emailCharacterCountFlow.collectAsState(initial = 0)

    LaunchedEffect(key1 = Unit, block = {
        ///tabsTwoViewModel.flowTest()
    })

    LaunchedEffect(key1 = Unit) {
        tabsTwoViewModel.collectEvent(lifecycle) { event ->
            when (event) {
                TabsTwoViewModel.Event.GoToHome -> {
                    appState.navController.navigateSingleTopTo(NavRoutes.Home().route)
                }
            }
        }
    }


    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Tab Screen",
                onClick = { appState.navController.navigateUp() }
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            // Здесь вы можете использовать emailCharacterCount в UI вашего Compose-экрана
            // Например:
            Text("Character count: $emailCharacterCount")
            if(uiState.isLoading) {
                CircularProgressIndicator(strokeWidth = 20.dp)
            }
            if(uiState.error) {
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = uiState.errorMessage
                )
            }

            TextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = uiState.login,
                onValueChange = tabsTwoViewModel::onLoginChange,
                enabled = uiState.isLoading.not(),
            )

            TextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = uiState.password,
                onValueChange = tabsTwoViewModel::onPasswordChange,
                enabled = uiState.isLoading.not(),
            )
            
            Button(onClick = tabsTwoViewModel::onLoginClick,) {
                Text("Login")            }
        }
    }
}