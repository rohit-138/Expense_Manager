package com.example.expensemanager.api

import com.example.expensemanager.models.LoginData
import com.example.expensemanager.models.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

//    @Headers(
//        "Content-Type:application/json"
//    )

    @POST("/api/auth/login")//for checking whether user is logged in or not
    suspend fun getLoginDetails(

    @Body login: LoginData
):Response<LoginResponse>
}