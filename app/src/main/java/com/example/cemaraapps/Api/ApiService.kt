package com.example.cemaraapps.Api

import com.example.cemaraapps.*
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
        @Header("Authorization") idToken:String,
        @Field("name") name: String
    ): Call<FamilyResponse>

    //Family join
    @FormUrlEncoded
    @POST("family")
    fun joinFamily(
        @Header("Authorization") idToken:String,
        @Field("id") id: String
    ): Call<FamilyJoinResponse>

    //Family get
    @GET("family")
    fun getFamily(
        @Header("Authorization") idToken:String
    ): Call<FamilyGetResponse>

    //task
    @GET("event")
    fun createEvent(
        @Header("Authorization") idToken:String,
        @Field("id") id:String
    ): Call<CreateEventResponse>
}
