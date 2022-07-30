package com.example.expensemanager.models

data class RegisterData(
    val name:String,
    val email:String,
    val password:String,
    val otp:String,
    val hashedotp: String

)

data class RegisterResponse(
    val success:Boolean,
    val token:String
)

data class SendOtpData(
    val email: String
)
data class SendOtpResponse(
    val success: Boolean,
    val otp:String,
    val hashedotp:String
)
