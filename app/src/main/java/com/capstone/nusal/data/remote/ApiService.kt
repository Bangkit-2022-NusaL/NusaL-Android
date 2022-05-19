package com.capstone.nusal.data.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST() // endpoint
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ) // return type

    @FormUrlEncoded
    @POST()
    suspend fun userRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    )


}