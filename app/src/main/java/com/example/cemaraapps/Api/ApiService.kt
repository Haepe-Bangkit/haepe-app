package com.example.cemaraapps.Api

import com.example.cemaraapps.FamilyResponse
import com.example.cemaraapps.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("token")
    fun getLogin(
        @Field("code") code: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("redirect_uri") redirect_uri: String,
        @Field("grant_type") grant_type: String
    ): Call<LoginResponse>

    //Family insert
    @FormUrlEncoded
    @POST("family")
    fun createFamily(
        @Field("familyId") familyId: String
    ): Call<FamilyResponse>

    @FormUrlEncoded
    @GET("family")
    fun getFamilyId(
        @Field("name") name: String,
        @Field("id") id: String
    ): Call<FamilyResponse>
}