package org.cheva.miniprojecttodolist.todo.dashboard.presentation

import org.cheva.miniprojecttodolist.todo.core.data.Todo

sealed interface DashboardEvent {
    data class OnTodoChecked(val isChecked: Boolean): DashboardEvent
    data class OnTodoClicked(val todo: Todo): DashboardEvent
    data object OnLoadTodo: DashboardEvent
}