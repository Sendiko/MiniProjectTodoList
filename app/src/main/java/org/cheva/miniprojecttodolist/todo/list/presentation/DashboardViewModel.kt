package org.cheva.miniprojecttodolist.todo.list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cheva.miniprojecttodolist.core.network.Retrofit
import org.cheva.miniprojecttodolist.core.preferences.AppPreferences
import org.cheva.miniprojecttodolist.core.preferences.dataStore
import org.cheva.miniprojecttodolist.todo.list.data.GetTodosResponse
import org.cheva.miniprojecttodolist.todo.list.data.TodosItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel(app: Application): AndroidViewModel(app) {

    private val prefs = AppPreferences(app.dataStore)
    private val apiService = Retrofit.getInstance()
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when(event) {
            is DashboardEvent.OnTodoChecked -> TODO()
            is DashboardEvent.OnTodoClicked -> openTodo(event.todo)
            DashboardEvent.OnLoadTodo -> getTodos()
        }
    }

    private fun openTodo(todo: TodosItem) {
        _state.update {
            it.copy(todo = todo)
        }
    }

    fun setNameAndToken(username: String, token: String) {
        viewModelScope.launch {
            prefs.saveToken(token = "Bearer $token")
        }
        _state.update {
            it.copy(name = username, token = token)
        }
    }

    private fun getTodos() {
        val token = "Bearer ${state.value.token}"
        viewModelScope.launch {
            apiService.getTodos(token)
                .enqueue(
                    object : Callback<GetTodosResponse> {
                        override fun onResponse(
                            call: Call<GetTodosResponse?>,
                            response: Response<GetTodosResponse?>
                        ) {
                            when(response.code()) {
                                200 -> _state.update {
                                    it.copy(todos = response.body()?.todos ?: emptyList())
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<GetTodosResponse?>,
                            t: Throwable
                        ) {

                        }

                    }
                )
        }
    }

}