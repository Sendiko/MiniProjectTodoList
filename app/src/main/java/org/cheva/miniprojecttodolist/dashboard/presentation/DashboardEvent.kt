package org.cheva.miniprojecttodolist.dashboard.presentation

import org.cheva.miniprojecttodolist.dashboard.data.Todo

sealed interface DashboardEvent {
    data class OnTodoChecked(val isChecked: Boolean): DashboardEvent
    data class OnTodoClicked(val todo: Todo): DashboardEvent
}