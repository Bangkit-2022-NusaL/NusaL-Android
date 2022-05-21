package com.capstone.nusal.data.remote

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    // TODO: Ganti jadi @Body

    @POST("/user/login") // endpoint
    suspend fun userLogin(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse

    @POST("/user/signup")
    suspend fun userRegister(
        @Body registerRequestBody: RegisterRequestBody
    ): RegisterResponse


}