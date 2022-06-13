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


    fun getUserId(): LiveData<UserId> {
        return pref.getId().asLiveData()
    }

}