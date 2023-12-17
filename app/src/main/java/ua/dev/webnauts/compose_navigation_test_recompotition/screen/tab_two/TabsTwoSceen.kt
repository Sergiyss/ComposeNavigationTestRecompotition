package ua.dev.webnauts.compose_navigation_test_recompotition.screen.tab_two

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import ua.dev.webnauts.compose_navigation_test_recompotition.AppState
import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.posts_comments.PostCommentsItem
import ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor.NetworkResponse

@Composable
fun TabsTwoScreen(
    appState: AppState,
    tabsTwoViewModel: TabsTwoViewModel = hiltViewModel(),
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current
) {
    var comments  by tabsTwoViewModel.comments

    LaunchedEffect(key1 = Unit, block = {
        tabsTwoViewModel.getComments()
    })

    when(comments){
        is NetworkResponse.Loading->{
            Box (modifier = Modifier.fillMaxSize(1f)){
                CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
        }

        is NetworkResponse.Success -> {
            (comments as NetworkResponse.Success<List<PostCommentsItem>>).data?.let {data->

                LazyColumn(
                    content = {
                        itemsIndexed(data) { index, it ->
                            Column(
                                modifier = Modifier
                                    .background(Color(0xFFB6B6B6))
                                    .height(150.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
                            ) {
                                Text(text = "# $index", fontSize = 22.sp, color = Color(0xFFFF6D00))

                                Text(text = "User email : ${it.email}")
                                Text(text = "User Name : ${it.name}")

                                Text(text = "User Comment : ${it.body}")
                            }
                        }
                    },
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                )
            }
        }

        else -> {
            //other error
        }
    }
}