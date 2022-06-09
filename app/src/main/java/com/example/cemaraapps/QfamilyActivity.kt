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
import androidx.appcompat.widget.AppCompatButton
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.IntroActivity.Companion.EXTRA_NAME2
import com.example.cemaraapps.databinding.ActivityQfamilyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QfamilyActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME3 = "EXTRA_NAME3"
    }
    private lateinit var binding: ActivityQfamilyBinding
    private lateinit var PopUpYesDialog: Dialog
    private lateinit var PopUpNoDialog: Dialog
    private lateinit var PopUpAskDialog: Dialog
    private lateinit var BtnInput: AppCompatButton
    private lateinit var BtnInputYes: AppCompatButton
    private lateinit var BtnInputNo: AppCompatButton
    private lateinit var TextInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQfamilyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PopUpYesDialog = Dialog(this)
        PopUpYesDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        PopUpYesDialog.setContentView(R.layout.activity_popup_yes)
        PopUpYesDialog.window?.setGravity(Gravity.BOTTOM)
        PopUpYesDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        PopUpAskDialog = Dialog(this)
        PopUpAskDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        PopUpAskDialog.setContentView(R.layout.activity_ask_create_family)

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
        TextInput = PopUpYesDialog.findViewById(R.id.et_join)
        val textInput = TextInput.text
        val nameIntro = intent.getStringExtra(EXTRA_NAME2)

        BtnInput.setOnClickListener {
        if(textInput.isNotEmpty()){
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(EXTRA_NAME3,nameIntro)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(applicationContext, "Mohon input text", Toast.LENGTH_SHORT).show()
        }
        }
    }
    private fun PopUpNo(){

        PopUpNoDialog.show()
        BtnInput = PopUpNoDialog.findViewById(R.id.btn_create)
        TextInput = PopUpNoDialog.findViewById(R.id.et_create)
        val textInput = TextInput.text
        val nameIntro = intent.getStringExtra(EXTRA_NAME2)

        BtnInput.setOnClickListener{
            if(textInput.isNotEmpty()){
                PopUpAskDialog.show()
                BtnInputYes = PopUpAskDialog.findViewById(R.id.btn_yes_ask)
                BtnInputNo = PopUpAskDialog.findViewById(R.id.btn_no_ask)
                BtnInputNo.setOnClickListener {
                    PopUpAskDialog.hide()
                }
                BtnInputYes.setOnClickListener {
                   // setCreateFam()
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra(EXTRA_NAME3,nameIntro)
                    startActivity(intent)
                    finish()
                }

            }else{
                Toast.makeText(applicationContext, "Mohon input text", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun setCreateFam(){
        ApiConfig.getApiService().createFamily("RidhoFamily")
            .enqueue(object: Callback<FamilyResponse> {
                override fun onResponse(
                    call: Call<FamilyResponse>,
                    response: Response<FamilyResponse>
                ) {
                    if (response.isSuccessful){
                       val user = response.body()
                       user!!.data.familyId.let { Log.d("familyId",it) }
                    }
                }

                override fun onFailure(call: Call<FamilyResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")

                }

            })
    }

}