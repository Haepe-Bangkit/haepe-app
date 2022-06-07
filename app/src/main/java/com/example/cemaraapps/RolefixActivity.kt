package com.example.cemaraapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cemaraapps.databinding.ActivityRolefixBinding

class RolefixActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRolefixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRolefixBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            myRoleFix.text = intent.getStringExtra(RoleActivity.EXTRA_DATA).toString()
            editButton.setOnClickListener {
                startActivity(Intent(this@RolefixActivity,RoleActivity::class.java))
            }
            backRolefix.setOnClickListener {
                super.onBackPressed()
            }
        }

    }
}