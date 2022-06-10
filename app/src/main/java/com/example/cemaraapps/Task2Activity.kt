package com.example.cemaraapps

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.databinding.ActivityTask2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Task2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTask2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)
//        setCreateTask()

        binding.apply {
            backTask.setOnClickListener {
                super.onBackPressed()
            }
            btnAddTask.setOnClickListener {
                Toast.makeText(applicationContext, "Task Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }

    }
//    private fun setCreateTask() {
//        ApiConfig.getApiService().createTask("")
//            .enqueue(object : Callback<TaskGetResponse> {
//                override fun onResponse(
//                    call: Call<TaskGetResponse>,
//                    response: Response<TaskGetResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val user = response.body()
//                        user!!.id.let { Log.d("taskId", it) }
//                    }
//                }
//
//                override fun onFailure(call: Call<TaskGetResponse>, t: Throwable) {
//                    Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
//
//                }
//
//            })
//    }
}