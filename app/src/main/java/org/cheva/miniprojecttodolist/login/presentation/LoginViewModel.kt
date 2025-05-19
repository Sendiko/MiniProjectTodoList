package org.cheva.miniprojecttodolist.login.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cheva.miniprojecttodolist.core.network.Retrofit
import org.cheva.miniprojecttodolist.core.preferences.AppPreferences
import org.cheva.miniprojecttodolist.core.preferences.dataStore
import org.cheva.miniprojecttodolist.login.data.LoginRequest
import org.cheva.miniprojecttodolist.login.data.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(app: Application): AndroidViewModel(app) {

    private val _prefs = AppPreferences(app.dataStore)
    private val _token = _prefs.getToken()
    private val apiService = Retrofit.getInstance()
    private val _state = MutableStateFlow(LoginState())
    val state = combine(_state, _token) { state, token ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LoginState())

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> changeEmail(event.name)
            is LoginEvent.OnPasswordChanged -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            LoginEvent.OnDismissDialog -> dismissDialog()
            LoginEvent.OnLoginClicked -> login()
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
        viewModelScope.launch {
            val request = LoginRequest(
                email = state.value.email,
                password = state.value.password
            )
            apiService.login(request = request)
                .enqueue(
                    object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse?>,
                            response: Response<LoginResponse?>
                        ) {
                            when (response.code()) {
                                200 -> {
                                    viewModelScope.launch { _prefs.saveToken(response.body()?.user?.token?:"") }
                                    viewModelScope.launch { _prefs.saveName(response.body()?.user?.name?:"") }
                                    _state.update {
                                        it.copy(
                                            successLogin = true,
                                            message = "Berhasil Login!",
                                            name = response.body()?.user?.name ?: "",
                                            token = response.body()?.user?.token ?: ""
                                        )
                                    }
                                }

                                400 -> _state.update {
                                    it.copy(message = "Password salah.")
                                }

                                404 -> _state.update {
                                    it.copy(message = "User tidak ditemukan.")
                                }

                                else -> _state.update {
                                    it.copy(message = "Server Error.")
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<LoginResponse?>,
                            t: Throwable
                        ) {
                            _state.update {
                                it.copy(message = "Server Error.")
                            }
                        }

                    }
                )
        }
    }

}