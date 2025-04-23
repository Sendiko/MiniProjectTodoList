package org.cheva.miniprojecttodolist.login.presentation

data class LoginState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val token: String = "",
    val passwordVisible: Boolean = false,
    val message: String = "",
    val successLogin: Boolean = false,
)

