package com.example.expensemanager.api.transaction

import com.example.expensemanager.api.auth.Myinterceptor
import com.example.expensemanager.utils.Constants.Companion.URL_BASE
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TransactionRetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(Myinterceptor())
    }.build()
    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val transactionApi:TransactionApi by lazy {
        retrofit.create(TransactionApi::class.java)
    }
}