package com.example.cemaraapps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cemaraapps.model.DataUser
import kotlinx.coroutines.launch

class MainViewModel (private val pref: UserPreferences) : ViewModel()  {

    fun getUser(): LiveData<DataUser> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}