package com.example.cemaraapps

import android.content.Intent
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
import com.example.cemaraapps.ClassHelper.PrefManager

class IntroActivity : AppCompatActivity() {

    private lateinit var btn_got_it: Button
    private val title_array = arrayOf(
        "Fitur Kalender", "Member's info",
        "Buat atau gabung family", "Grafik yang keren"
    )
    private var prefManager: PrefManager? = null
    private val description_array = arrayOf(
        "Dengan fitur ini, anda menambahkan tugas harian bersama keluarga",
        "Anda juga perlu mengisi data keahlian anda",
        "Anda dapat mengetahui detail family grup anda, anda dapat membuat family baru atau pun join menggunakan kode Token",
        "Anda dapat melihat grafik interaktif sebagai bentuk pembagian tugas keluarga anda"
    )
    private val about_images_array = intArrayOf(
        R.drawable.calender_intro, R.drawable.members_intro,
        R.drawable.detail_intro, R.drawable.chart_intro
    )
    private val color_array = intArrayOf(
        R.color.black, R.color.gold,
        R.color.oren, R.color.deep_blue
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extra_name = intent.getStringExtra(LoginActivity.EXTRA_NAME)

        prefManager = PrefManager(this)
        if (!prefManager!!.isFirstTimeLaunch) {
            prefManager!!.isFirstTimeLaunch == true
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME2,extra_name)
            startActivity(intent)
            finish()
        }
        setContentView(R.layout.activity_intro)

        initComponent()

    }

    private fun initComponent() {
        val extra_name = intent.getStringExtra(LoginActivity.EXTRA_NAME)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        btn_got_it = findViewById(R.id.btn_got_it)
        bottomProgressDots(0)
        val myViewPagerAdapter: MyViewPagerAdapter = MyViewPagerAdapter()
        viewPager.adapter = myViewPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

        btn_got_it.setVisibility(View.GONE)
        btn_got_it.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME2,extra_name)
            startActivity(intent)
        })
        findViewById<View>(R.id.btn_skip).setOnClickListener {
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME2,extra_name)
            startActivity(intent)
        }
    }

    private fun bottomProgressDots(index: Int) {
        val dotsLayout = findViewById<LinearLayout>(R.id.layoutDots)
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