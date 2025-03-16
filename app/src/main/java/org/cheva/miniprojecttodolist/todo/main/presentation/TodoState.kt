package org.cheva.miniprojecttodolist.todo.main.presentation

import org.cheva.miniprojecttodolist.todo.dashboard.presentation.component.Category

data class TodoState(
    val title: String = "",
    val description: String = "",
    val message: String = "",
    val category: Category? = null,
    val successLogin: Boolean = false,
)
