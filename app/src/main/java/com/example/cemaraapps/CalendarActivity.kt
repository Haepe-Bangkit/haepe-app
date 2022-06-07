package com.example.cemaraapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cemaraapps.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            backRole.setOnClickListener {
                super.onBackPressed()
            }
        btnTodoList.setOnClickListener {
            startActivity(Intent(this@CalendarActivity, TaskActivity::class.java))
        }
        }
    }
}