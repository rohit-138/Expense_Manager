package com.example.expensemanager.api.transaction

import com.example.expensemanager.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TransactionApi {

    @POST("/api/expense/addTransaction")
    suspend fun addTransaction(
        @Body transaction: TransactionData,
        @Header("auth-token") auth:String
    ):Response<TransactionResponse>

    @POST("/api/expense/getMontlyIncomeAndExpense")
    suspend fun getMontlyIncomeAndExpense(
        @Body body:getMontlyIncomeAndExpenseData,
        @Header("auth-token") auth: String
    ):Response<getMontlyIncomeAndExpenseResponse>

    @POST("/api/expense/getMonthlyTransactions")
    suspend fun getMonthlyTransactions(
        @Body body:getTransactionData,
        @Header("auth-token") auth: String
    ):Response<List<TransactionX>>
}