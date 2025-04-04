package org.cheva.miniprojecttodolist.network

import org.cheva.miniprojecttodolist.register.data.RegisterRequest
import org.cheva.miniprojecttodolist.register.data.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

}