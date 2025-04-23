package org.cheva.miniprojecttodolist.todo.list.presentation

import org.cheva.miniprojecttodolist.todo.list.data.TodosItem

data class DashboardState(
    val name: String = "",
    val token: String = "",
    val todos: List<TodosItem> = emptyList(),
    val todo: TodosItem? = null,
)
