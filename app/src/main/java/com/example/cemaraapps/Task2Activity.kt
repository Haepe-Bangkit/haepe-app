package com.example.cemaraapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cemaraapps.databinding.ActivityTask2Binding

class Task2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTask2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            backTask.setOnClickListener {
                super.onBackPressed()
            }
            btnAddTask.setOnClickListener {
                Toast.makeText(applicationContext, "Task Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }

    }
}