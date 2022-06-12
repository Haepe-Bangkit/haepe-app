package com.example.cemaraapps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.cemaraapps.LoginActivity.Companion.EXTRA_NAME
import com.example.cemaraapps.databinding.ActivityIntroBinding
import com.example.cemaraapps.model.IntroItem
import java.text.FieldPosition

class IntroActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME2 = "EXTRA_NAME2"
    }
    private lateinit var introAdapter: IntroAdapter
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var binding: ActivityIntroBinding
    lateinit var preferences: SharedPreferences
    val pref_show_intro = "Intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nameLogin = intent.getStringExtra(EXTRA_NAME)

        preferences = getSharedPreferences("Intro Slider",Context.MODE_PRIVATE)
        if(!preferences.getBoolean(pref_show_intro,true)){
            val intent = Intent(this,QfamilyActivity::class.java)
            intent.putExtra(EXTRA_NAME2, nameLogin)
            startActivity(intent)
            finish()
        }

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonGetStarted.isVisible = false
        setIntroItems()
        setupIndicators()
        setCurrentIndicator(0)
        binding.apply {
            skipIntro.setOnClickListener{
                intentAndPreferences()
            }
            buttonGetStarted.setOnClickListener {
                intentAndPreferences()
            }
        }
    }
    private fun intentAndPreferences(){
        val nameLogin = intent.getStringExtra(EXTRA_NAME)
        val editor = preferences.edit()
        editor.putBoolean(pref_show_intro,false)
        editor.apply()
        val intent = Intent(this@IntroActivity,QfamilyActivity::class.java)
        intent.putExtra(EXTRA_NAME2, nameLogin)
        startActivity(intent)
        finish()
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
        ItemIntroViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                if(position == 3){
                    binding.buttonGetStarted.isVisible = true
                }
            }
        })

        (ItemIntroViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

    }
    private fun setupIndicators(){
        indicatorContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(introAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount=indicatorContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if( i== position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}
