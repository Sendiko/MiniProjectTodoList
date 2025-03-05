package org.cheva.miniprojecttodolist.login

sealed interface LoginEvent {
    data class OnEmailChanged(val username: String): LoginEvent
    data class OnPasswordChanged(val password: String): LoginEvent
    data class OnPasswordVisibilityChanged(val isVisible: Boolean): LoginEvent
    object OnLoginClicked: LoginEvent
}