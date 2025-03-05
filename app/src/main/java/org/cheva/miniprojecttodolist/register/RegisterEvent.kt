package org.cheva.miniprojecttodolist.register

sealed interface RegisterEvent {
    data class OnNameChanged(val name: String): RegisterEvent
    data class OnEmailChanged(val email: String): RegisterEvent
    data class OnPasswordChanged(val password: String): RegisterEvent
    data class OnPasswordVisibilityChanged(val isVisible: Boolean): RegisterEvent
    object OnRegisterClicked: RegisterEvent
}