package org.cheva.miniprojecttodolist.todo.main.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category

class TodoViewModel: ViewModel() {

    private val _state = MutableStateFlow(TodoState())
    val state = _state.asStateFlow()

    fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.OnTitleChanged -> changeTitle(event.title)
            is TodoEvent.OnDescriptionChanged -> changeDescription(event.description)
            is TodoEvent.OnCategoryChanged -> changeCategory(event.category)
            TodoEvent.OnSaveTodoClicked -> saveTodo()
        }
    }

    private fun changeTitle(title: String) {
        _state.update { it.copy(
            title = title
        ) }
    }

    private fun changeDescription(description: String) {
        _state.update { it.copy(
            description = description
        ) }
    }

    private fun changeCategory(category: Category) {
        _state.update { it.copy(
            category = category
        ) }
    }

    private fun saveTodo() {
        TODO("Store todo via Retrofit")
    }
}