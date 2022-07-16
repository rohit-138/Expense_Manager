package com.example.expensemanager.repository

import com.example.expensemanager.api.auth.RetrofitInstance
import com.example.expensemanager.models.*
import retrofit2.Response

class Repository {
    suspend fun getLoginDetails(login:LoginData):Response<LoginResponse> {
        return RetrofitInstance.api.getLoginDetails(login)
    }
    suspend fun pushRegisterDetails(registerData:RegisterData):Response<RegisterResponse>{
        return RetrofitInstance.api.pushRegisterDetails(registerData)
    }
    suspend fun pushSendOtp(sendOtpData: SendOtpData):Response<SendOtpResponse>{
        return RetrofitInstance.api.pushSendOtp(sendOtpData)
    }

}