package ua.dev.webnauts.compose_navigation_test_recompotition.screen.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ua.dev.webnauts.compose_navigation_test_recompotition.AppState
import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.list_photos.PhotosResponseItem
import ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor.NetworkResponse

@Composable
fun HomeScreen(
    appState: AppState,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val sss by viewModel.getPokemonDitto2
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getPokemonDitto()

        viewModel.getPokemonDitto2()
    })

    Column(
    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
    horizontalAlignment = Alignment.Start,
    ) {




        when(sss){

            is NetworkResponse.Success -> {
                (sss as NetworkResponse.Success<List<PhotosResponseItem>>).data?.let {datas->

                    Column(modifier = Modifier) {

                        LazyColumn(content = {
                            items(items = datas){
                                Text(text = it.title)
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = it.url)
                                            .apply(block = fun ImageRequest.Builder.() {
                                                size(Size.ORIGINAL)
                                            }).build(),

                                        ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .height(100.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }

                        })

                    }
                }
            }
            is NetworkResponse.Loading -> {
                Box(modifier = Modifier
                    .fillMaxSize(1f)
                    .align(Alignment.CenterHorizontally)) {
                    CircularProgressIndicator()
                }
            }
            else->{
                Box(modifier = Modifier
                    .fillMaxSize(1f)
                    .align(Alignment.CenterHorizontally)) {
                    Text(text = "ERROR")
                }
            }
        }
    }

}

