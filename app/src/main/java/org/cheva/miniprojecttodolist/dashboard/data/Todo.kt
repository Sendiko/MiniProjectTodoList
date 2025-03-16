package org.cheva.miniprojecttodolist.dashboard.data

import org.cheva.miniprojecttodolist.dashboard.presentation.component.Category

data class Todo(
    val title: String = "",
    val description: String = "",
    val category: Category = Category.WORK,
    val isCompleted: Boolean = false
)
