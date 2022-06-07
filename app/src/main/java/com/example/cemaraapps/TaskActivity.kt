package com.example.cemaraapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cemaraapps.databinding.ActivityTaskBinding

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
        }
        binding.btnAddTask.setOnClickListener {

            Toast.makeText(applicationContext, "Task Berhasil ditambahkan", Toast.LENGTH_SHORT).show()

            val taskActivity = hashMapOf(
                "title" to binding.etTitle.text.toString(),
                "date" to binding.etDate.text.toString(),
                "start" to binding.etStart.text.toString(),
                "end" to binding.etEnd.text.toString(),
                "who" to binding.etWho.text.toString(),
                "Description" to binding.etDes.text.toString(),

                )
//            firestore.collection("List Task").document(binding.etTitle.text.toString()).set(taskActivity)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Task Added", Toast.LENGTH_LONG).show()
//                }.addOnFailureListener {
//                    Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
//                }
            super.onBackPressed()
        }
    }
}