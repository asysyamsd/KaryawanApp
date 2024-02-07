package com.example.karyawanapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {
        fun getApiService(): ApiInterface {

            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()

                .baseUrl("http://192.168.1.7/karyawanAPI/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

                return retrofit.create(ApiInterface::class.java)
        }
    }
}