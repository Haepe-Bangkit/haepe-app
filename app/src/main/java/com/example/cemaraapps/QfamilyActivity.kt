package com.example.cemaraapps

import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.databinding.ActivityQfamilyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
//    private fun setCreateFam(){
//        ApiConfig.getApiService().getLogin(code, client_id, client_secret,grant_type,redirect_uri)
//            .enqueue(object: Callback<LoginResponse> {
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val user = response.body()
//                        user!!.access_token.let { Log.e("access_token",it) }
//                        user!!.client_id?.let { Log.e("id", it) }
//                        user!!.code?.let { Log.e("code", it) }
//                        user!!.client_secret.let { Log.e("client_sec",it) }
//                        user!!.grant_type.let { Log.d("grant",it) }
//                        user!!.redirect_uri.let{ Log.d("uri",it)}
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
//                }
//
//            })
//    }

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