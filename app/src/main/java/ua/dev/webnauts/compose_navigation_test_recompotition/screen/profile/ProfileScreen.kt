package ua.dev.webnauts.compose_navigation_test_recompotition.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ua.dev.webnauts.compose_navigation_test_recompotition.AppState
import ua.dev.webnauts.compose_navigation_test_recompotition.R
import ua.dev.webnauts.compose_navigation_test_recompotition.navigation.Graph
import ua.dev.webnauts.compose_navigation_test_recompotition.ui.compose_components.Button.AppButton
import ua.dev.webnauts.compose_navigation_test_recompotition.ui.compose_ui.top_bars.DefaultTopBar
import ua.dev.webnauts.compose_navigation_test_recompotition.ui.theme.customShapes
import ua.dev.webnauts.compose_navigation_test_recompotition.ui.theme.spacing

@Composable
fun ProfileScreen(
    appState: AppState,
    profileViewModel: ProfileViewModel = hiltViewModel() ){

    LaunchedEffect(key1 = Unit, block = {
        profileViewModel.getUserData()
    })

    val user by profileViewModel.getUser

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Registration Screen",
                onClick = { appState.navController.navigateUp() }
            )
        }) {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = MaterialTheme.spacing.medium)){
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top,
            ) {
                Surface(
                    modifier = Modifier,
                    shape = MaterialTheme.customShapes.half,
                    color = Color.White,
                    elevation = 6.dp
                ) {
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = rememberAsyncImagePainter(
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(R.drawable.test_image)
                                .build()
                        ),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }

                Column {
                    Text(text = "Name: ${user?.user?.fullName}", style = MaterialTheme.typography.body1)
                    Text(text = "Email: ${user?.user?.email}", style = MaterialTheme.typography.body1)
                    Text(text = "Phone: ${user?.user?.phone}", style = MaterialTheme.typography.body1)
                }
            }


            AppButton(text = "Exit") {

                profileViewModel.exitUser(
                    user?.user?.email!!,
                ){
                    appState.navController.navigate(Graph.Login.graph){
                        launchSingleTop = true
                    }
                }
            }

//            Button(onClick = {
//                appState.navController.navigate(Graph.Login.graph)
//            }) {
//                Text(text = "Go to Registration")
//            }
        }
    }
}