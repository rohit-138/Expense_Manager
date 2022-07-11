package com.example.expensemanager.repository

import com.example.expensemanager.api.RetrofitInstance
import com.example.expensemanager.models.LoginData
import com.example.expensemanager.models.LoginResponse
import retrofit2.Response

class Repository {
suspend fun getLoginDetails(login:LoginData):Response<LoginResponse> {
    return RetrofitInstance.api.getLoginDetails(login)
}

}