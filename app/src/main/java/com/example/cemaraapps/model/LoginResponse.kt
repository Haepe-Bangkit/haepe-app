package com.example.cemaraapps

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class LoginResponse(
    @field:SerializedName("idToken")
    val idToken: String,
    @field:SerializedName("userId")
    val userId: String
    )

data class FamilyResponse(
//    @field:SerializedName("statusCode")
//    val statusCode: String,
//    @field:SerializedName("error")
//    val error: String,
//    @field:SerializedName("message")
//    val message: String,
        @field:SerializedName("familyId")
        val familyId: String
//    @field:SerializedName("id")
//    val id: String
)
