package com.example.expensemanager.api.auth

import com.example.expensemanager.models.*
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

    @POST("/api/auth/sendOtp")
    suspend fun pushSendOtp(
        @Body sendotpdata:SendOtpData
    ):Response<SendOtpResponse>


}