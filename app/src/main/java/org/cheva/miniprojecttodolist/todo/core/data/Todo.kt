package org.cheva.miniprojecttodolist.todo.core.data

import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category

data class Todo(
    val title: String = "",
    val description: String = "",
    val category: Category = Category.WORK,
    val isCompleted: Boolean = false
)
