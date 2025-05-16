package org.cheva.miniprojecttodolist.todo.list.presentation

import org.cheva.miniprojecttodolist.todo.list.data.TodosItem

sealed interface DashboardEvent {
    data class OnTodoChecked(val isChecked: Boolean): DashboardEvent
    data class OnTodoClicked(val todo: TodosItem): DashboardEvent
    data object OnLoadTodo: DashboardEvent
}