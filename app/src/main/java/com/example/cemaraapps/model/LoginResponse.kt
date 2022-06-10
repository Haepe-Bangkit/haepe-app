package com.example.cemaraapps

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class LoginResponse(
    @field:SerializedName("statusCode")
    val statusCode: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: dataResponse
    )
data class dataResponse(
    @field:SerializedName("idToken")
    val idToken: String
)

data class FamilyResponse(
    @field:SerializedName("statusCode")
    val statusCode: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: familyInsertResponse
)
data class familyInsertResponse(
    @field:SerializedName("familyId")
    val familyId: String
)

data class FamilyGetResponse(
    @field:SerializedName("statusCode")
    val statusCode: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: familyGetResponse
)
data class familyGetResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: bodyGetResponse,
    @field:SerializedName("members")
    val message: ArrayList<membersResponse>
)
data class bodyGetResponse(
    @field:SerializedName("calenderId")
    val calenderId: String,
    @field:SerializedName("name")
    val name: String
)
data class membersResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: membersBodyResponse
)
data class membersBodyResponse(
    @field:SerializedName("aclId")
    val aclId: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("role")
    val role: String
)

data class FamilyJoinResponse(
    @field:SerializedName("statusCode")
    val statusCode: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: familyJoinResponse
)
data class familyJoinResponse(
    @field:SerializedName("id")
    val id: String
)
