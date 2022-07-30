package com.example.expensemanager.repository

import com.example.expensemanager.api.auth.RetrofitInstance
import com.example.expensemanager.api.transaction.TransactionRetrofitInstance
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
    suspend fun addTransaction(transactionData: TransactionData,auth:String):Response<TransactionResponse>{
        return TransactionRetrofitInstance.transactionApi.addTransaction(transactionData,auth)
    }
    suspend fun getMontlyIncomeAndExpense(body:getMontlyIncomeAndExpenseData,auth: String):Response<getMontlyIncomeAndExpenseResponse>{
        return TransactionRetrofitInstance.transactionApi.getMontlyIncomeAndExpense(body,auth)
    }

    suspend fun getMonthlyTransactions(body:getTransactionData,auth: String):Response<List<TransactionX>>{
        return TransactionRetrofitInstance.transactionApi.getMonthlyTransactions(body,auth)
    }

}