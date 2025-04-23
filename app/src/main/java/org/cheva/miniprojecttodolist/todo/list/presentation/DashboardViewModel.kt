package org.cheva.miniprojecttodolist.todo.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cheva.miniprojecttodolist.core.network.Retrofit
import org.cheva.miniprojecttodolist.todo.list.data.GetTodosResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel: ViewModel() {

    private val apiService = Retrofit.getInstance()
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when(event) {
            is DashboardEvent.OnTodoChecked -> TODO()
            is DashboardEvent.OnTodoClicked -> TODO()
            DashboardEvent.OnLoadTodo -> getTodos()
        }
    }

    fun setNameAndToken(username: String, token: String) {
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