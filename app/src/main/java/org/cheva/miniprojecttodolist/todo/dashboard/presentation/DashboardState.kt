package org.cheva.miniprojecttodolist.todo.dashboard.presentation

import org.cheva.miniprojecttodolist.todo.dashboard.data.TodosItem

data class DashboardState(
    val name: String = "",
    val todos: List<TodosItem> = emptyList(),
    val todo: TodosItem? = null,
)
