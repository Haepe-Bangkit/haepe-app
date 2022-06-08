package com.example.cemaraapps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.cemaraapps.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private val description_array = arrayOf(
        "Dengan fitur ini, anda menambahkan tugas harian bersama keluarga",
        "Anda juga perlu mengisi data keahlian anda",
        "Anda dapat mengetahui detail family grup anda, anda dapat membuat family baru atau pun join menggunakan kode Token",
        "Anda dapat melihat grafik interaktif sebagai bentuk pembagian tugas keluarga anda"
    )
    private val title_array = arrayOf(
        "Fitur Kalender", "Member's info",
        "Buat atau gabung family", "Grafik yang keren"
    )
    private val about_images_array = intArrayOf(
        R.drawable.calender_intro, R.drawable.members_intro,
        R.drawable.detail_intro, R.drawable.chart_intro
    )
    private val color_array = intArrayOf(
        R.color.black, R.color.gold,
        R.color.oren, R.color.deep_blue
    )


    private lateinit var binding: ActivityIntroBinding
    private lateinit var btn_got_it: Button
    lateinit var preference : SharedPreferences
    val pref_show_intro = "Intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extra_name = intent.getStringExtra(LoginActivity.EXTRA_NAME)
        preference = getSharedPreferences("Intro", Context.MODE_PRIVATE)
        if(!preference.getBoolean(pref_show_intro,true)){
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME2,extra_name)
            startActivity(intent)
            finish()
        }

        preference.edit().putBoolean(pref_show_intro,false)
        val viewPager = binding.viewPager
        btn_got_it = binding.btnGotIt
        bottomProgressDots(0)
        val myViewPagerAdapter = MyViewPagerAdapter()
        viewPager.adapter = myViewPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        binding.btnGotIt.setVisibility(View.GONE)
        binding.btnGotIt.setOnClickListener{
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME2,extra_name)
            startActivity(intent)
            finish()
        }
        binding.btnSkip.setOnClickListener {
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME2,extra_name)
            startActivity(intent)
            finish()

        }

    }

    private fun bottomProgressDots(index: Int) {
        val dotsLayout = binding.layoutDots
        val dots = arrayOfNulls<ImageView>(MAX_STEP)
        dotsLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val width_height = 15
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams(width_height, width_height))
            params.setMargins(10, 10, 10, 10)
            dots[i]!!.layoutParams = params
            dots[i]!!.setImageResource(R.drawable.dot)
            dots[i]!!.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_IN)
            dotsLayout.addView(dots[i])
        }
        dots[index]!!.setImageResource(R.drawable.dot)
        dots[index]!!.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_IN)
    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            bottomProgressDots(position)
            if (position == title_array.size - 1) {
                btn_got_it!!.visibility = View.VISIBLE
            } else {
                btn_got_it!!.visibility = View.GONE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    inner class MyViewPagerAdapter internal constructor() : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.item_intro, container, false)
            (view.findViewById<View>(R.id.title) as TextView).text = title_array[position]
            (view.findViewById<View>(R.id.description) as TextView).text =
                description_array[position]
            (view.findViewById<View>(R.id.image) as ImageView).setImageResource(
                about_images_array[position]
            )
            view.findViewById<View>(R.id.lyt_parent).setBackgroundColor(
                resources.getColor(
                    color_array[position]
                )
            )
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return title_array.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }

    companion object {
        private const val MAX_STEP = 4
        const val EXTRA_NAME2 = "EXTRA_NAME2"
    }
}