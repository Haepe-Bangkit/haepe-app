package com.example.cemaraapps

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.cemaraapps.databinding.ActivityQfamilyBinding
import java.util.*

class QfamilyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQfamilyBinding
    private lateinit var PopUpYesDialog: Dialog
    private lateinit var PopUpNoDialog: Dialog
    private lateinit var BtnInput: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQfamilyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        PopUpYesDialog = Dialog(this)
        PopUpYesDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        PopUpYesDialog.setContentView(R.layout.activity_popup_yes)
        PopUpYesDialog.window?.setGravity(Gravity.BOTTOM)
        PopUpYesDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


        PopUpNoDialog = Dialog(this)
        PopUpNoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        PopUpNoDialog.setContentView(R.layout.activity_popup_no)
        PopUpNoDialog.window?.setGravity(Gravity.BOTTOM)
        PopUpNoDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.apply {
            btnYes.setOnClickListener{
                PopUpYes()
            }
            btnNo.setOnClickListener {
                PopUpNo()
            }
        }

    }

    private fun PopUpYes(){
        PopUpYesDialog.show()

        BtnInput = PopUpYesDialog.findViewById(R.id.btn_join)
        BtnInput.setOnClickListener{
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
    private fun PopUpNo(){
        PopUpNoDialog.show()
        BtnInput = PopUpNoDialog.findViewById(R.id.btn_create)

        val NomorToken = randomCode()
        BtnInput.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(EXTRA_CODE,NomorToken)
            startActivity(intent)
        }
    }

    private fun randomCode():String{
        val alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val charArray=CharArray(8)
        val random = Random()
        for(i in 0..7){
            val randomlnt= random.nextInt(alphabet.length)
            charArray[i]=alphabet[randomlnt]
        }
        return charArray.joinToString("")
    }

    companion object{
        const val EXTRA_CODE = "EXTRA_CODE"
        const val EXTRA_FAM = "EXTRA_FAM"
    }
}