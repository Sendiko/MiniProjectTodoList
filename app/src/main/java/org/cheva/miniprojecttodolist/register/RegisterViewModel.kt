package org.cheva.miniprojecttodolist.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnNameChanged -> changeName(event.name)
            is RegisterEvent.OnEmailChanged -> changeEmail(event.email)
            is RegisterEvent.OnPasswordChanged -> changePassword(event.password)
            is RegisterEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            RegisterEvent.OnRegisterClicked -> register()
        }
    }

    private fun changeName(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }

    private fun changeEmail(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    private fun changePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun changePasswordVisibility(isVisible: Boolean) {
        _state.update {
            it.copy(passwordVisible = isVisible)
        }
    }

    private fun register() {
        if (state.value.name.isEmpty())
            _state.update {
                it.copy(message = "Name is required")
            }
        return

        if (state.value.email.isEmpty())
            _state.update {
                it.copy(message = "Email is required")
            }
        return

        if (state.value.password.isEmpty())
            _state.update {
                it.copy(message = "Password is required")
            }
        return

        _state.update {
            it.copy(message = "Register Successful", successRegister = true)
        }

    }

}