package ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor

object HttpRoutes {
    private const val BASE_URL_STAJ = "https://jsonplaceholder.typicode.com/"
    private const val BASE_URL_TEST = "https://jsonplaceholder.typicode.com/"
    private const val BASE_URL_COM = "https://jsonplaceholder.typicode.com/"

    const val BASE_URL = BASE_URL_STAJ

    const val photos = "$BASE_URL/photos/10"

    const val photoss = "$BASE_URL/albums/1/photos"

    const val comments = "$BASE_URL/posts/1/comments"
}