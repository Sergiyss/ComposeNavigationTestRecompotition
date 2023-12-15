package ua.dev.webnauts.compose_navigation_test_recompotition.network.data.users


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseItem(
    @SerialName("companyId")
    val companyId: Int = 0,
    @SerialName("countryCode")
    val countryCode: String = "",
    @SerialName("firstName")
    val firstName: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("lastName")
    val lastName: String = ""
)
