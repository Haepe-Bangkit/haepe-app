package com.example.cemaraapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.cemaraapps.databinding.ActivityRoleBinding

class RoleActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "EXTRA_DATA"
    }


    private lateinit var binding: ActivityRoleBinding
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataMinat = mutableListOf<String>()
        val dataSkill = mutableListOf<String>()

        radioButton1 = binding.Father
        radioButton2 = binding.Mother
        radioButton3 = binding.Children

        binding.apply {
            backRole.setOnClickListener{
                super.onBackPressed()
            }


            confirmMembers.setOnClickListener {
                var pil = ""
                when {
                    radioButton1.isChecked -> pil = "Ayah"
                    radioButton2.isChecked -> pil = "Ibu"
                    radioButton3.isChecked -> pil = "anak"
                }
                when{
                    Makanan.isChecked -> dataMinat.add("Makanan")
                    Olahraga.isChecked -> dataMinat.add("Olahraga")
                    Alam.isChecked -> dataMinat.add("Alam")
                    Teknologi.isChecked -> dataMinat.add("Teknologi")
                    Hiburan.isChecked -> dataMinat.add("Hiburan")
                    Edukasi.isChecked -> dataMinat.add("Edukasi")
                    Animasi.isChecked -> dataMinat.add("Animasi")
                    Binatang.isChecked -> dataMinat.add("Binatang")
                    Keuangan.isChecked -> dataMinat.add("Keuangan")
                    Literatur.isChecked -> dataMinat.add("Literatur")
                    Kesehatan.isChecked -> dataMinat.add("Kesehatan")
                }
                when{
                    Masak.isChecked -> dataSkill.add("Masak")
                    Swakarya.isChecked -> dataSkill.add("Swakarya")
                    Sosialisasi.isChecked -> dataSkill.add("Sosialisasi")
                    Manajemen.isChecked -> dataSkill.add("Manajemen")
                    Otomotif.isChecked -> dataSkill.add("Otomotif")
                    Kelistrikan.isChecked -> dataSkill.add("Kelistrikan")
                    Berkebun.isChecked -> dataSkill.add("Berkebun")
                }

                Toast.makeText(applicationContext, "ini: "+pil, Toast.LENGTH_SHORT).show()

                super.onBackPressed()
                finish()
            }
        }


    }
}