package com.example.cemaraapps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.cemaraapps.databinding.ActivityIntroBinding
import com.example.cemaraapps.model.IntroItem

class IntroActivity : AppCompatActivity() {
    private lateinit var introAdapter: IntroAdapter
    private lateinit var binding: ActivityIntroBinding
    lateinit var preferences: SharedPreferences
    val pref_show_intro = "Intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = getSharedPreferences("Intro Slider",Context.MODE_PRIVATE)

        if(!preferences.getBoolean(pref_show_intro,true)){
            startActivity(Intent(this,QfamilyActivity::class.java))
            finish()
        }
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setIntroItems()

        binding.apply {
            skipIntro.setOnClickListener{
                val editor = preferences.edit()
                editor.putBoolean(pref_show_intro,false)
                editor.apply()
                startActivity(Intent(this@IntroActivity,QfamilyActivity::class.java))
            }
        }

    }
    private fun setIntroItems(){
        introAdapter = IntroAdapter(
            listOf(
                IntroItem(
                imageIntro = R.drawable.calender_intro,
                titleIntro = "Atur tugas anda",
                descIntro = "Dengan fitur ini, anda menambahkan tugas harian bersama keluarga"
                ),
                IntroItem(
                    imageIntro = R.drawable.members_intro,
                    titleIntro = "Atur tugas anda",
                    descIntro = "Anda juga perlu mengisi data keahlian anda"
                ),
                IntroItem(
                    imageIntro = R.drawable.detail_intro,
                    titleIntro = "Buat atau gabung family",
                    descIntro = "Anda dapat mengetahui detail family grup anda, anda dapat membuat family baru atau pun join menggunakan kode Token"
                ),
                IntroItem(
                    imageIntro = R.drawable.chart_intro,
                    titleIntro = "Grafik yang keren",
                    descIntro = "Anda dapat melihat grafik interaktif sebagai bentuk pembagian tugas keluarga anda"
                ),
                ))
        val ItemIntroViewPager = findViewById<ViewPager2>(R.id.Intro_viewPager)
        ItemIntroViewPager.adapter = introAdapter



    }
}
