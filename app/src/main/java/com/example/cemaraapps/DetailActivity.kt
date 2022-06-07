package com.example.cemaraapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cemaraapps.MainActivity.Companion.EXTRA_CODE2
import com.example.cemaraapps.databinding.ActivityDetailBinding
import com.example.cemaraapps.databinding.ActivityPopupNoBinding
import com.example.cemaraapps.model.DetailClass
import java.util.*


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val kode = intent.getStringExtra(EXTRA_CODE2)

        binding.apply {
            backDetail.setOnClickListener {
                super.onBackPressed()
            }
            nameFam.text = "sf"
            tokenFam.text = kode
        }


    }

}