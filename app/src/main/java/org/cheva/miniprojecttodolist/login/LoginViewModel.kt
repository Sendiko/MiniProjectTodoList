package org.cheva.miniprojecttodolist.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event){
            is LoginEvent.OnNameChanged -> changeEmail(event.name)
            is LoginEvent.OnPasswordChanged -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            LoginEvent.OnDismissDialog -> dismissDialog()
            LoginEvent.OnLoginClicked -> login()
        }
    }

    private fun changeEmail(email: String) {
        _state.update {
            it.copy(name = email)
        }
    }

    private fun changePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun changePasswordVisibility(isVisible: Boolean) {
        _state.update {
            it.copy(
                passwordVisible = isVisible
            )
        }
    }

    private fun dismissDialog() {
        _state.update {
            it.copy(
                message = "",
                successLogin = false
            )
        }
    }

    private fun login() {

    }

}