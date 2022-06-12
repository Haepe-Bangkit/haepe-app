package com.example.cemaraapps

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class DetailActivity : AppCompatActivity() {
    var member = ArrayList<membersResponse>()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        getDetailFam()

        binding.apply {
            backDetail.setOnClickListener {
                super.onBackPressed()
            }
//            nameFam.text =
        }


    }
    private fun getDetailFam() {
        ApiConfig.getApiService().getFamily("efewf")
            .enqueue(object : Callback<FamilyGetResponse> {
                override fun onResponse(
                    call: Call<FamilyGetResponse>,
                    response: Response<FamilyGetResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()!!
                        binding.nameFam.text= responseBody.data.body.name
                        binding.tokenFam.text = responseBody.data.id
                        member = responseBody.data.members
                    }
                }

                override fun onFailure(call: Call<FamilyGetResponse>, t: Throwable) {
                    Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }

            })
    }


}