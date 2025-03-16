package org.cheva.miniprojecttodolist.todo.dashboard.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.cheva.miniprojecttodolist.todo.core.data.Todo

class DashboardViewModel: ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when(event) {
            is DashboardEvent.OnTodoChecked -> TODO()
            is DashboardEvent.OnTodoClicked -> setTask(event.todo)
        }
    }

    private fun setTask(todo: Todo) {
        _state.update {
            it.copy(todo = todo)
        }
    }
}