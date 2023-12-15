package ua.dev.webnauts.compose_navigation_test_recompotition.screen.tab_two

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
    val userDatabaseManagement: UserDatabaseManagement
) : ViewModel(), UiStateDelegate<
        TabsTwoViewModel.UiState, TabsTwoViewModel.Event> by UiStateDelegateImpl(UiState()) {

    data class UiState(
        val isLoading: Boolean = false,
        val error: Boolean = false,
        val errorMessage: String = "Error loading tabs",
        val title: String = "4432",
        val login: String = "",
        val password: String = "",
    )

    sealed interface Event {
        object GoToHome : Event
    }

    /***
     * Получаю пользователя в IO потоке и после переобразую его в маин потоке
     * */
    val userFlow: Flow<UserProfile?> = flow {
        delay(3000)
        emit(userDatabaseManagement.getUser())
    }.flowOn(Dispatchers.IO)

    // Здесь мы преобразуем Flow, чтобы выполнить подсчет символов на главном потоке
    val emailCharacterCountFlow: Flow<String> = userFlow
        .map { user ->
            user?.user!!.email
        }
        .flowOn(Dispatchers.Main)

    private fun countEmailCharacters(email: String): Int {
        // Ваш код для подсчета символов в email
        return email.length
    }

    fun onLoginChange(login: String) {
        asyncUpdateUiState(viewModelScope) { state -> state.copy(login = login) }
    }

    fun onPasswordChange(password: String) {
        asyncUpdateUiState(viewModelScope) { state -> state.copy(password = password) }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            updateUiState { state -> state.copy(isLoading = true) }
            if (uiState.login == "nik") {
                sendEvent(Event.GoToHome)
            } else {
                updateUiState { state ->
                    state.copy(
                        isLoading = true,
                        error = true
                    )
                }
            }

        }.invokeOnCompletion { asyncUpdateUiState(viewModelScope) { state -> state.copy(isLoading = false) } }
    }

}