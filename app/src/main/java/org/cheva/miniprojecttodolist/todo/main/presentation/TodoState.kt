package org.cheva.miniprojecttodolist.todo.main.presentation

import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category

data class TodoState(
    val id: String? = null,
    val title: String = "",
    val description: String = "",
    val message: String = "",
    val category: Category? = null,
    val isSelectingCategory: Boolean = false,
    val success: Boolean = false,
    val token: String = "",
)
