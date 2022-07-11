package com.example.expensemanager.api

import com.example.expensemanager.models.LoginData
import com.example.expensemanager.models.LoginResponse
import com.example.expensemanager.models.RegisterData
import com.example.expensemanager.models.RegisterResponse
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

    @POST("/api/auth/register")
    suspend fun pushRegisterDetails(
        @Body registerData:RegisterData
    ):Response<RegisterResponse>


}