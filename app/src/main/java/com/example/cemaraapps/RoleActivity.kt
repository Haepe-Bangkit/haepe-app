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

        radioButton1 = binding.Father
        radioButton2 = binding.Mother
        radioButton3 = binding.Children

        binding.apply {
            backRole.setOnClickListener{
                super.onBackPressed()
            }


            confirmMembers.setOnClickListener {
                var pil = "as"
                if(radioButton1.isChecked){
                    pil = "Father"
                }
                Toast.makeText(applicationContext, "ini: "+pil, Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, RolefixActivity::class.java)
                startActivity(intent)
                intent.putExtra(EXTRA_DATA,pil)
                finish()
            }
        }


    }
}