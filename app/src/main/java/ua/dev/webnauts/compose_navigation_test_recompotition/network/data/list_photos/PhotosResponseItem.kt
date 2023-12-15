package ua.dev.webnauts.compose_navigation_test_recompotition.network.data.list_photos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotosResponseItem(
    @SerialName("albumId")
    val albumId: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("url")
    val url: String = ""
)

@Serializable
data class PhotosResponse(
    val photos: List<PhotosResponseItem>
)
