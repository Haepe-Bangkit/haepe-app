package com.example.cemaraapps

import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.CalendarActivity.Companion.EXTRA_DATE
import com.example.cemaraapps.databinding.ActivityTaskBinding
import com.example.cemaraapps.model.UserId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private lateinit var taskActivityViewModel: TaskActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskActivityViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[taskActivityViewModel::class.java]

        //get user ID

        binding.apply {
            backTask.setOnClickListener {
                super.onBackPressed()
            }


        //StartTime
            startTimeButton.setOnClickListener {
                val currentTimeStart = Calendar.getInstance()
                val startHour = currentTimeStart.get(Calendar.HOUR_OF_DAY)
                val startMinute = currentTimeStart.get(Calendar.MINUTE)
                TimePickerDialog(this@TaskActivity, { _, HourOfDay, Minute->
                    etStart.setText("$HourOfDay:$Minute:00+07.00")
                },startHour,startMinute,false).show()
            }
        //EndTime
            EndTimeButton.setOnClickListener{
                val currentTimeStart = Calendar.getInstance()
                val startHour = currentTimeStart.get(Calendar.HOUR_OF_DAY)
                val startMinute = currentTimeStart.get(Calendar.MINUTE)
                TimePickerDialog(this@TaskActivity, { _, HourOfDay, Minute->
                    etEnd.setText("$HourOfDay:$Minute:00+07.00")
                },startHour,startMinute,false).show()
            }

            btnNextAddTask.setOnClickListener {

                createTask("115299601309963900866")
                Toast.makeText(applicationContext, "Lanjutkan untuk membuat task", Toast.LENGTH_SHORT).show()

                super.onBackPressed()
            }
        }

    }

    private fun createTask(idMember:String) {

                val summary = binding.etTitle.text.toString()
                val description = binding.etDesc.text.toString()
                val startTime = binding.etDate.text.toString()+binding.etStart.text.toString()
                val endTime = binding.etDate.text.toString()+binding.etEnd.text.toString()

            taskActivityViewModel.getUser().observe(this){ user->

                binding.etDate.text = intent.getStringExtra(EXTRA_DATE).toString()+"T"
                ApiConfig.getApiService().createEvent("Bearer ${user.idToken}",startTime,endTime,summary,description,idMember)
                .enqueue(object : Callback<CreateEventResponse> {

                    override fun onResponse(
                        call: Call<CreateEventResponse>,
                        response: Response<CreateEventResponse>
                    ) {
                        if (response.isSuccessful){
                            val responseBody = response.body()
                            if (responseBody != null){
                                Toast.makeText(applicationContext, "Berhasil menambahkan event", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                        }
                    }

                    override fun onFailure(call: Call<CreateEventResponse>, t: Throwable) {
                        Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")

                    }

                })
            }
    }


}