package ua.dev.webnauts.compose_navigation_test_recompotition.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.dev.webnauts.compose_navigation_test_recompotition.database.UserDatabaseManagement
import ua.dev.webnauts.compose_navigation_test_recompotition.database.UserProfile
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val userDatabaseManagement: UserDatabaseManagement
) : ViewModel() {

    private val _getUser = mutableStateOf<UserProfile?>(null)
    val getUser: State<UserProfile?> get() = _getUser

    fun getUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            _getUser.value = userDatabaseManagement.getUser()
        }
    }

    fun exitUser(userId : String, success: () -> Unit){
        userDatabaseManagement.deleteUser(userId)
        success()
    }
}