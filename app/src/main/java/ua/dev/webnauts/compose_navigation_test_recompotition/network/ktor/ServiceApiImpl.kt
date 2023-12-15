package ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.list_photos.PhotosResponse
import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.list_photos.PhotosResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceApiImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val client: HttpClient,

) : ServiceApi, SafeApiCall() {

    override suspend fun photos(): NetworkResponse<PhotosResponseItem>{
        return safeCall(context) {
            client.get(HttpRoutes.photos) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    override suspend fun photoss(): NetworkResponse<List<PhotosResponseItem>> {
        return safeCall(context) {
            client.get(HttpRoutes.photoss) {
                contentType(ContentType.Application.Json)
            }
        }
    }

}