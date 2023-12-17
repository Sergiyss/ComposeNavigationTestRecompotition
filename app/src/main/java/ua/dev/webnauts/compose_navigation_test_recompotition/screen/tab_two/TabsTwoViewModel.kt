package ua.dev.webnauts.compose_navigation_test_recompotition.screen.tab_two

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ua.dev.webnauts.compose_navigation_test_recompotition.database.UserDatabaseManagement
import ua.dev.webnauts.compose_navigation_test_recompotition.database.UserProfile
import ua.dev.webnauts.compose_navigation_test_recompotition.network.data.posts_comments.PostCommentsItem
import ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor.NetworkResponse
import ua.dev.webnauts.compose_navigation_test_recompotition.network.ktor.ServiceApi
import ua.dev.webnauts.compose_navigation_test_recompotition.state.UiStateDelegate
import ua.dev.webnauts.compose_navigation_test_recompotition.state.UiStateDelegateImpl
import javax.inject.Inject

/**
 * два варианта чистого подхода, но второй где дата не очень подходит
 *
 * UiState
 * **/
@HiltViewModel
class TabsTwoViewModel @Inject constructor(
    val userDatabaseManagement: UserDatabaseManagement,
    private val serviceApi: ServiceApi,
) : ViewModel() {

    var comments = mutableStateOf<NetworkResponse<List<PostCommentsItem>>?>(null)
        private set

    fun getComments(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = ArrayList<PostCommentsItem>()
            for (i in 0..12){
                serviceApi.postsComments().also { res->
                    if(res is NetworkResponse.Success){
                        res.data?.forEach {
                            list.add(it)
                        }
                    }
                }
            }

            comments.value = NetworkResponse.Success(data = list)
        }
    }


}