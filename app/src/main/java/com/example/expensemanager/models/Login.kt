package com.example.expensemanager.models

data class LoginData(
    val email:String,
    val password:String
)
data class LoginResponse(
    val success:Boolean,
    val token:String
)
