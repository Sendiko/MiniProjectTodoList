package org.cheva.miniprojecttodolist.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cheva.miniprojecttodolist.core.network.Retrofit
import org.cheva.miniprojecttodolist.register.data.RegisterRequest
import org.cheva.miniprojecttodolist.register.data.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {

    private val apiService = Retrofit.getInstance()
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnNameChanged -> changeName(event.name)
            is RegisterEvent.OnEmailChanged -> changeEmail(event.email)
            is RegisterEvent.OnPasswordChanged -> changePassword(event.password)
            is RegisterEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            RegisterEvent.OnRegisterClicked -> register()
            RegisterEvent.OnDismissDialog -> dismissDialog()
        }
    }

    private fun dismissDialog() {
        _state.update { it.copy(message = "", successRegister = false) }
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
        if (state.value.name.isEmpty()){
            _state.update {
                it.copy(message = "Name is required")
            }
            return
        }

        if (state.value.email.isEmpty()){
            _state.update {
                it.copy(message = "Email is required")
            }
            return
        }

        if (state.value.password.isEmpty()){
            _state.update {
                it.copy(message = "Password is required")
            }
            return
        }

        viewModelScope.launch {
            val request = RegisterRequest(
                name = state.value.name,
                email = state.value.email,
                password = state.value.password
            )
            apiService.register(request)
                .enqueue(
                    object : Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse?>,
                            response: Response<RegisterResponse?>
                        ) {
                            when(response.code()) {
                                201 -> _state.update {
                                    it.copy(message = "Berhasil Register!", successRegister = true, name = response.body()?.user?.name ?: "null")
                                }
                                500 -> _state.update {
                                    it.copy(message = "Server error.")
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<RegisterResponse?>,
                            t: Throwable
                        ) {
                            _state.update {
                                it.copy(message = "Can't connect to the server.")
                            }
                        }

                    }
                )
        }

    }

}