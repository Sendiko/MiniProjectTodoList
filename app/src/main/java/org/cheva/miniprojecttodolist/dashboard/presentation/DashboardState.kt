package org.cheva.miniprojecttodolist.dashboard.presentation

import org.cheva.miniprojecttodolist.dashboard.data.Todo

data class DashboardState(
    val name: String = "",
    val todos: List<Todo> = emptyList(),
    val todo: Todo? = null,
)
