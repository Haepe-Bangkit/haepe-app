package com.example.cemaraapps.Api

import com.example.cemaraapps.FamilyResponse
import com.example.cemaraapps.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    fun getLogin(
        @Field("idToken") idToken: String
    ): Call<LoginResponse>

    //Family insert
    @FormUrlEncoded
    @POST("family")
    fun createFamily(
        @Header("Authorization") Authorization:String,
        @Field("name") name: String
    ): Call<FamilyResponse>

    @FormUrlEncoded
    @GET("family")
    fun getFamilyId(
        @Field("name") name: String,
        @Field("id") id: String
    ): Call<FamilyResponse>
}