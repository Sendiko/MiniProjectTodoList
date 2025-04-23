package org.cheva.miniprojecttodolist.core.network

import org.cheva.miniprojecttodolist.register.data.RegisterRequest
import org.cheva.miniprojecttodolist.register.data.RegisterResponse
import org.cheva.miniprojecttodolist.todo.list.data.GetTodosResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

    @GET("todo")
    fun getTodos(
        @Header("Authorization") token: String
    ): Call<GetTodosResponse>

}