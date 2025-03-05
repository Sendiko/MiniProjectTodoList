package org.cheva.miniprojecttodolist.register

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val message: String = "",
    val successRegister: Boolean = false,
)
