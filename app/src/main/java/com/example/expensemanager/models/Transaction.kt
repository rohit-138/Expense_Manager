package com.example.expensemanager.models

import java.util.*

data class TransactionData(
    val type:String,
    val amount:Int,
    val month:Int,
    val note:String
)

data class TransactionResponse(
    val success:Boolean
)
data class getMontlyIncomeAndExpenseResponse(
    val success: Boolean,
    val income:Int,
    val expense: Int
)
data class getMontlyIncomeAndExpenseData(
    val month: Int
)
data class getTransactionResponse(
    val _id:String,
    val user:String,
    val type:String,
    val amount: Int,
    val note: String,
    val month: Int,
    val date:Date,
    val createAt:Date,
    val updatedAt:Date
)
data class getTransactionData(
    val month: Int
)

