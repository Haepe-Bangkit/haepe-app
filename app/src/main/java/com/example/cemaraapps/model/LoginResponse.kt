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
    val data: famGetResponse
)
data class famGetResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: bodyGetResponse,
    @field:SerializedName("members")
    val members: ArrayList<membersResponse>
)
data class bodyGetResponse(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("calenderId")
    val calenderId: String
)
data class membersResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: membersBodyResponse
)
data class membersBodyResponse(
    @field:SerializedName("role")
    val role: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("aclId")
    val aclId: String
)

data class FamilyJoinResponse(
    @field:SerializedName("statusCode")
    val statusCode: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: famJoinResponse
)
data class famJoinResponse(
    @field:SerializedName("id")
    val id: String
)
//response create/insert event
data class CreateEventResponse(
    @field:SerializedName("statusCode")
    val statusCode: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: eventCreateResponse
)
data class eventCreateResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: bodyInsertResponse
)
data class bodyInsertResponse(
    @field:SerializedName("creator")
    val creator: String,
    @field:SerializedName("start")
    val start: bodyInsertStartResponse,
    @field:SerializedName("end")
    val end: bodyInsertEndResponse,
    @field:SerializedName("summary")
    val summary: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("assignFor")
    val assignFor: String,
    @field:SerializedName("eventId")
    val eventId: String
)
data class bodyInsertStartResponse(
    @field:SerializedName("_seconds")
    val _seconds: String,
    @field:SerializedName("_nanoseconds")
    val _nanoseconds: String
)
data class bodyInsertEndResponse(
    @field:SerializedName("_seconds")
    val _seconds: String,
    @field:SerializedName("_nanoseconds")
    val _nanoseconds: String
)

//response get event_todo list
data class GetEventResponse(
    @field:SerializedName("statusCode")
    val statusCode: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: eventGetResponse
)
data class eventGetResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: bodyGetEventsResponse,
    @field:SerializedName("members")
    val members: ArrayList<membersGetResponse>,
    @field:SerializedName("events")
    val events: ArrayList<eventsListGetResponse>
)
data class bodyGetEventsResponse(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("calendarId")
    val calendarId: String
)
data class membersGetResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: bodyGetMembersResponse,
)
data class bodyGetMembersResponse(
    @field:SerializedName("role")
    val role: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("aclId")
    val aclId: String
)
data class eventsListGetResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: ListMembersResponse,
)
data class ListMembersResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("body")
    val body: DetailListMembersResponse,
)
data class DetailListMembersResponse(
    @field:SerializedName("creator")
    val creator: CreatorListMembersResponse,
    @field:SerializedName("eventId")
    val eventId: String,
    @field:SerializedName("summary")
    val summary: String,
    @field:SerializedName("end")
    val end: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("assignFor")
    val assignFor: assignForDetailResponse,
    @field:SerializedName("start")
    val start: String
)
data class CreatorListMembersResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String
)
data class assignForDetailResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String
)

