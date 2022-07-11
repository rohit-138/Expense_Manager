package com.example.expensemanager.models

data class RegisterData(
    val email:String,
    val password:String
)

data class RegisterResponse(
    val success:Boolean,
    val token:String
)
