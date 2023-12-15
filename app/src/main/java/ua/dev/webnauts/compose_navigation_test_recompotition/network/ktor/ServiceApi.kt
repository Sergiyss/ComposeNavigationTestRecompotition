package ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor

import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.list_photos.PhotosResponse
import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.list_photos.PhotosResponseItem

interface ServiceApi {

    suspend fun photos() : NetworkResponse<PhotosResponseItem>
    suspend fun photoss() : NetworkResponse<List<PhotosResponseItem>>
}