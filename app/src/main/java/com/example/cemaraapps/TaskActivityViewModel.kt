package com.example.cemaraapps

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.model.DataUser
import com.example.cemaraapps.model.UserId
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskActivityViewModel(private val pref: UserPreferences) : ViewModel() {
    fun getUser(): LiveData<DataUser> {
        return pref.getUser().asLiveData()
    }
        fun getFamily(idToken: String) {
                ApiConfig.getApiService().getFamily(idToken)
                    .enqueue(object : Callback<FamilyGetResponse> {
                        override fun onResponse(
                            call: Call<FamilyGetResponse>,
                            response: Response<FamilyGetResponse>,
                        ) {
                            if (response.isSuccessful){
                                val responseBody = response.body()
                                if (responseBody != null){
                                    val sizeMember = responseBody.data.members.size

                                    val idMember = UserId(
                                        responseBody.data.members[sizeMember].id
                                    )
                                    saveId(idMember)
                                }

                            } else {
                            }
                        }

                        override fun onFailure(call: Call<FamilyGetResponse>, t: Throwable) {
                        }

                    })

    }
    fun saveId(user: UserId) {
        viewModelScope.launch {
            pref.saveUserId(user)
        }
    }
    fun getUserId(): LiveData<UserId> {
        return pref.getId().asLiveData()
    }

}