package com.example.cemaraapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cemaraapps.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        binding.apply {
            ibCalendar.setOnClickListener{
                startActivity(Intent(this@MainActivity, CalendarActivity::class.java))
            }
            ibDetail.setOnClickListener{
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(intent)

            }
            ibMember.setOnClickListener {
                startActivity(Intent(this@MainActivity, RoleActivity::class.java))
            }
            btnLogout.setOnClickListener {
                mGoogleSignInClient.signOut()
                Toast.makeText(applicationContext, "Berhasil logout", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
            ibChart.setOnClickListener{
                startActivity(Intent(this@MainActivity, ChartActivity::class.java))
            }

            var nama = intent.getStringExtra(IntroActivity.EXTRA_NAME2).toString()
            nama+="  "
            var index = 0
            var index2 = 0
            for(huruf in nama){
                index+=1
                if(huruf==' '){
                    index2+=1
                    if(index2==2){
                        tvName.text = nama.substring(0,index-1)
                        return
                    }
                }
            }
        }

    }

}