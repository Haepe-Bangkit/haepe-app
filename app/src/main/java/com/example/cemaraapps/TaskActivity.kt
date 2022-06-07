package com.example.cemaraapps

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cemaraapps.CalendarActivity.Companion.EXTRA_DATE
import com.example.cemaraapps.databinding.ActivityTaskBinding
import java.util.*

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            backTask.setOnClickListener {
                super.onBackPressed()
            }
            btnAddTask.setOnClickListener {
                Toast.makeText(applicationContext, "Task Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
            etDate.text = intent.getStringExtra(EXTRA_DATE)
            startTimeButton.setOnClickListener {
                val currentTimeStart = Calendar.getInstance()
                val startHour = currentTimeStart.get(Calendar.HOUR_OF_DAY)
                val startMinute = currentTimeStart.get(Calendar.MINUTE)
                TimePickerDialog(this@TaskActivity, { View, HourOfDay, Minute->
                    etStart.setText("$HourOfDay : $Minute")
                },startHour,startMinute,false).show()
            }
        }

    }
}