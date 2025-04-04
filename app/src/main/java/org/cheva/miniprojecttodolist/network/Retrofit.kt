package org.cheva.miniprojecttodolist.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object Retrofit {

    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
            }.also {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                it.addInterceptor(logging)
            }.build()
    }

}