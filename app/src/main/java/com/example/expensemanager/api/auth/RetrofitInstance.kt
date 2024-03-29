package com.example.expensemanager.api.auth

import com.example.expensemanager.utils.Constants.Companion.URL_BASE
import com.example.expensemanager.utils.Constants.Companion.URL_LOCALHOST
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(Myinterceptor())
    }.build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
//            .baseUrl(URL_LOCALHOST)wont work for some reason todo find out why it will not work
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}