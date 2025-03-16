package org.cheva.miniprojecttodolist.todo.dashboard.presentation

import org.cheva.miniprojecttodolist.todo.core.data.Todo

data class DashboardState(
    val name: String = "",
    val todos: List<Todo> = emptyList(),
    val todo: Todo? = null,
)
