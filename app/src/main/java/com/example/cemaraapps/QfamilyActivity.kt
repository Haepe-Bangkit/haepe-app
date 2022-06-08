package com.example.cemaraapps

import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.databinding.ActivityQfamilyBinding
import com.example.cemaraapps.model.familyRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class QfamilyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQfamilyBinding
    private lateinit var PopUpYesDialog: Dialog
    private lateinit var PopUpNoDialog: Dialog
    private lateinit var BtnInput: AppCompatButton
    private lateinit var TextInput : EditText
//    private lateinit var et_create: EditText

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
        TextInput = PopUpNoDialog.findViewById(R.id.et_create)
        setCreateFam(TextInput.toString())
        BtnInput.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
//            postCreateFamily("")
        }

    }
//
//    private fun postCreateFamily(name: String) {
//        ApiConfig.getApiService().createFamily(name)
//            .enqueue(object: Callback<FamilyResponse> {
//                override fun onResponse(
//                    call: Call<FamilyResponse>,
//                    response: Response<FamilyResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val user = response.body()
//                        user!!.familyId.let { Log.e("name",it) }
//                    }
//                }
//
//                override fun onFailure(call: Call<FamilyResponse>, t: Throwable) {
//                    Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
//
//                }
//
//            })
//    }

    private fun setCreateFam(name: String){
        ApiConfig.getApiService().createFamily(name)
            .enqueue(object: Callback<FamilyResponse> {
                override fun onResponse(
                    call: Call<FamilyResponse>,
                    response: Response<FamilyResponse>
                ) {
                    if (response.isSuccessful){
                        val user = response.body()
                        user!!.familyId.let { Log.e("name",it) }
                    }
                }

                override fun onFailure(call: Call<FamilyResponse>, t: Throwable) {
                    Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")

                }

            })
    }

    companion object{
        const val EXTRA_CODE = "EXTRA_CODE"
        const val EXTRA_FAM = "EXTRA_FAM"
    }
}