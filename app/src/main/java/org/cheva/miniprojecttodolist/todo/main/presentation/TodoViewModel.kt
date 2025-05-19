package org.cheva.miniprojecttodolist.todo.main.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cheva.miniprojecttodolist.core.network.Retrofit
import org.cheva.miniprojecttodolist.core.preferences.AppPreferences
import org.cheva.miniprojecttodolist.core.preferences.dataStore
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category
import org.cheva.miniprojecttodolist.todo.main.data.get.GetTodoResponse
import org.cheva.miniprojecttodolist.todo.main.data.post.PostTodoRequest
import org.cheva.miniprojecttodolist.todo.main.data.post.PostTodoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(app: Application) : AndroidViewModel(app) {

    private val apiService = Retrofit.getInstance()
    private val preferences = AppPreferences(app.dataStore)
    private val _token = preferences.getToken()
    private val _state = MutableStateFlow(TodoState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TodoState())

    fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.OnTitleChanged -> changeTitle(event.title)
            is TodoEvent.OnDescriptionChanged -> changeDescription(event.description)
            is TodoEvent.OnCategoryChanged -> changeCategory(event.category)
            TodoEvent.OnSaveTodoClicked -> if (state.value.id == null)
                saveTodo()
            else updateTodo()
            is TodoEvent.OnDropdownChanged -> changeDropdown(event.isExpanded)
            TodoEvent.FetchTodo -> fetchTodo()
        }
    }

    fun setId(id: String?) {
        _state.update { it.copy(id = id) }
    }

    private fun changeDropdown(isExpanded: Boolean) {
        _state.update { it.copy(isSelectingCategory = isExpanded) }
    }

    private fun changeTitle(title: String) {
        _state.update {
            it.copy(
                title = title
            )
        }
    }

    private fun changeDescription(description: String) {
        _state.update {
            it.copy(
                description = description
            )
        }
    }

    private fun changeCategory(category: Category) {
        _state.update {
            it.copy(
                category = category
            )
        }
    }

    private fun updateTodo() {

    }

    private fun saveTodo() {
        viewModelScope.launch {
            val request = PostTodoRequest(
                description = state.value.description,
                title = state.value.title,
                category = state.value.category.toString(),
                isDone = true
            )
            apiService.postTodo(
                request = request,
                token = "Bearer ${state.value.token}"
            ).enqueue(
                object : Callback<PostTodoResponse> {
                    override fun onResponse(
                        call: Call<PostTodoResponse?>,
                        response: Response<PostTodoResponse?>
                    ) {
                        when (response.code()) {
                            201 -> _state.update {
                                it.copy(successPost = true)
                            }

                            401 -> _state.update {
                                it.copy(successPost = false, message = "Unauthorized")
                            }

                            500 -> _state.update {
                                it.copy(successPost = false, message = "Internal Server Error")
                            }
                        }
                    }

                    override fun onFailure(
                        call: Call<PostTodoResponse?>,
                        t: Throwable
                    ) {
                        _state.update {
                            it.copy(successPost = false, message = "Internal Server Error")
                        }
                    }

                }
            )
        }
    }

    private fun fetchTodo() {
        viewModelScope.launch {
            if (!state.value.id.isNullOrBlank()) {
                apiService.getTodo(
                    id = state.value.id!!,
                    token = "Bearer ${state.value.token}"
                ).enqueue(
                    object : Callback<GetTodoResponse> {
                        override fun onResponse(
                            call: Call<GetTodoResponse?>,
                            response: Response<GetTodoResponse?>
                        ) {
                            when (response.code()) {
                                200 -> _state.update {
                                    it.copy(
                                        title = response.body()?.todo?.title ?: "",
                                        description = response.body()?.todo?.description ?: "",
                                        category = Category.valueOf(
                                            response.body()?.todo?.category ?: ""
                                        )
                                    )
                                }

                                401 -> _state.update {
                                    it.copy(successPost = false, message = "Unauthorized")
                                }

                                500 -> _state.update {
                                    it.copy(successPost = false, message = "Internal Server Error")
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<GetTodoResponse?>,
                            t: Throwable
                        ) {
                            _state.update {
                                it.copy(successPost = false, message = "Internal Server Error")
                            }
                        }

                    }
                )
            }
        }
    }
}