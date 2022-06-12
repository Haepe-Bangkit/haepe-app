package com.example.cemaraapps

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.model.DataFamily
import com.example.cemaraapps.model.DataUser
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel (private val pref: UserPreferences) : ViewModel(){


     fun setLogin(idToken: String) {
        ApiConfig.getApiService().getLogin(idToken)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        if(user!=null){
                            val model = DataUser(
                                user.data.idToken,
                                true
                            )
                            saveUser(model)
                            user.data.idToken.let { Log.d("idTokenAPI",it) }
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }

            })
    }

    fun getFamily(idToken: String){

    }

    fun saveFamily(family: DataFamily) {
        viewModelScope.launch {
            pref.saveFamily(family)
        }
    }
    fun getUser(): LiveData<DataUser> {
        return pref.getUser().asLiveData()
    }
    fun saveUser(user: DataUser) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }


}