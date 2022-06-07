package com.capstone.nusal.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/user/login")
    suspend fun userLogin(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse

    @POST("/user/signup")
    suspend fun userRegister(
        @Body registerRequestBody: RegisterRequestBody
    ): RegisterResponse

    @POST("/password-reset")
    suspend fun userResetPassword(
        @Body resetPasswordRequestBody: ResetPasswordRequestBody
    ): ResetPasswordResponse
}