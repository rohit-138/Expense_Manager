package com.example.expensemanager.models

data class AllTransactionResponse(
    val success: Boolean,
    val transactions: List<TransactionX>
)