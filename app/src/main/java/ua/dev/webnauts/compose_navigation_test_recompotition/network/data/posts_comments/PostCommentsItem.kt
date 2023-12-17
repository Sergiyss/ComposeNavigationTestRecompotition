package ua.dev.webnauts.compose_navigation_test_recompotition.network.data.posts_comments


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostCommentsItem(
    @SerialName("body")
    val body: String = "",
    @SerialName("email")
    val email: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("postId")
    val postId: Int = 0
)