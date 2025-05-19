package org.cheva.miniprojecttodolist.core.network

import org.cheva.miniprojecttodolist.login.data.LoginRequest
import org.cheva.miniprojecttodolist.login.data.LoginResponse
import org.cheva.miniprojecttodolist.register.data.RegisterRequest
import org.cheva.miniprojecttodolist.register.data.RegisterResponse
import org.cheva.miniprojecttodolist.todo.list.data.GetTodosResponse
import org.cheva.miniprojecttodolist.todo.main.data.post.PostTodoRequest
import org.cheva.miniprojecttodolist.todo.main.data.post.PostTodoResponse
import org.cheva.miniprojecttodolist.todo.main.data.update.UpdateTodoRequest
import org.cheva.miniprojecttodolist.todo.main.data.update.UpdateTodoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("register")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

    @POST("login")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("todo")
    fun getTodos(
        @Header("Authorization") token: String
    ): Call<GetTodosResponse>

    @POST("todo")
    fun postTodo(
        @Body request: PostTodoRequest,
        @Header("Authorization") token: String
    ): Call<PostTodoResponse>

    @PUT("/todo/{id}")
    fun updateTodo(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Body request: UpdateTodoRequest
    ): Call<UpdateTodoResponse>

}