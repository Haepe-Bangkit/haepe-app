package com.example.cemaraapps

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import com.example.cemaraapps.databinding.ActivityCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarBinding
    private lateinit var datePicker: DatePicker
    private lateinit var cal : Calendar

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cal = Calendar.getInstance()
        datePicker = binding.datePick
        datePicker.setOnDateChangedListener { _, i, i2, i3 ->
            cal.set(Calendar.YEAR, i)
            cal.set(Calendar.MONTH, i2)
            cal.set(Calendar.DAY_OF_MONTH,i3)
        }

        binding.apply {
            backCalender.setOnClickListener {
                super.onBackPressed()
            }
            btnTodoList.setOnClickListener {
                startActivity(Intent(this@CalendarActivity, TodoListActivity::class.java))
                datePicker = datePick
            }
            addButton.setOnClickListener {
                cal.get(Calendar.YEAR)
                cal.get(Calendar.MONTH)
                cal.get(Calendar.DAY_OF_MONTH)

                val myFormat = "dd - MMMM - yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                val tanggal = sdf.format(cal.time)

                intent = Intent(this@CalendarActivity, TaskActivity::class.java)
                intent.putExtra(EXTRA_DATE, tanggal)
                startActivity(intent)
            }
        }

    }
    companion object{
        const val EXTRA_DATE = "EXTRA_DATE"
    }
}