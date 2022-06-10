package com.example.cemaraapps.Api

import com.example.cemaraapps.FamilyGetResponse
import com.example.cemaraapps.FamilyJoinResponse
import com.example.cemaraapps.FamilyResponse
import com.example.cemaraapps.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    fun getLogin(
        @Header("Authorization") Authorization: String,
        @Field("idToken") idToken: String
    ): Call<LoginResponse>

    //Family insert
    @FormUrlEncoded
    @POST("family")
    fun createFamily(
        @Header("Authorization") Authorization:String,
        @Field("name") name: String
    ): Call<FamilyResponse>

    //Family join
    @FormUrlEncoded
    @POST("family")
    fun joinFamily(
        @Field("id") id: String
    ): Call<FamilyJoinResponse>

    //Family get
    @FormUrlEncoded
    @GET("family")
    fun getFamily(): Call<FamilyGetResponse>
}
