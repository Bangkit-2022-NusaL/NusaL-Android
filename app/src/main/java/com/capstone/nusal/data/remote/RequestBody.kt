package com.capstone.nusal.data.remote

data class LoginRequestBody(
    val email: String,
    val password: String
)

data class RegisterRequestBody(
    val name: String,
    val email: String,
    val password: String
)

data class ResetPasswordRequestBody(
    val email: String,
    val password: String
)