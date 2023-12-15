package ua.dev.webnauts.compose_navigation_test_recompotition.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.list_photos.PhotosResponse
import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.list_photos.PhotosResponseItem
import ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor.NetworkResponse
import ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor.ServiceApi
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val serviceApi: ServiceApi,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var getPokemonDitto = mutableStateOf<NetworkResponse<PhotosResponseItem>?>(null)
        private set

    var getPokemonDitto2 = mutableStateOf<NetworkResponse<List<PhotosResponseItem>>?>(null)
        private set
    fun getPokemonDitto() {
        viewModelScope.launch(Dispatchers.Default) {
            getPokemonDitto.value = serviceApi.photos()
        }
    }

    fun getPokemonDitto2() {
        viewModelScope.launch(Dispatchers.Default) {
            getPokemonDitto2.value = serviceApi.photoss()
        }
    }
}