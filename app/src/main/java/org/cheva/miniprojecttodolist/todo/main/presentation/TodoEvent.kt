package org.cheva.miniprojecttodolist.todo.main.presentation

import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category

sealed interface TodoEvent {
    data class OnTitleChanged(val title: String): TodoEvent
    data class OnDescriptionChanged(val description: String): TodoEvent
    data class OnCategoryChanged(val category: Category): TodoEvent
    data class OnDropdownChanged(val isExpanded: Boolean): TodoEvent
    data object FetchTodo: TodoEvent
    object OnSaveTodoClicked: TodoEvent
}
