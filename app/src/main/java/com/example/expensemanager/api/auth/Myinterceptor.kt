package com.example.expensemanager.api.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class Myinterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request:Request=chain.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .build()
        return chain.proceed(request)
    }
}