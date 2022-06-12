package com.example.cemaraapps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cemaraapps.model.DataFamily
import com.example.cemaraapps.model.DataUser


class QfamilyViewModel (private val pref : UserPreferences): ViewModel() {

    fun getUser(): LiveData<DataUser> {
        return pref.getUser().asLiveData()
    }


}