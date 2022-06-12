package com.example.cemaraapps

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.IntroActivity.Companion.EXTRA_NAME2
import com.example.cemaraapps.databinding.ActivityQfamilyBinding
import com.example.cemaraapps.model.DataUser
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class QfamilyActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME3 = "EXTRA_NAME3"
    }
    private lateinit var qfamilyViewModel: QfamilyViewModel
    private lateinit var binding: ActivityQfamilyBinding
    private lateinit var PopUpYesDialog: Dialog
    private lateinit var PopUpNoDialog: Dialog
    private lateinit var BtnInput: AppCompatButton
    private lateinit var TextInput: EditText
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQfamilyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        qfamilyViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[QfamilyViewModel::class.java]

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
        TextInput = PopUpYesDialog.findViewById(R.id.et_join)
        progressBar = PopUpYesDialog.findViewById(R.id.progressBarJoin)

        var textInput = TextInput.text
        val nameIntro = intent.getStringExtra(EXTRA_NAME2)

        BtnInput.setOnClickListener {

        if(textInput.isNotEmpty()){
            progressBar.visibility = View.VISIBLE
            qfamilyViewModel.getUser().observe(this@QfamilyActivity){ user ->
                val client = ApiConfig.getApiService().joinFamily("Bearer ${user.idToken}",textInput.toString())
                client.enqueue(object : Callback<FamilyJoinResponse> {
                    override fun onResponse(
                        call: Call<FamilyJoinResponse>,
                        response: Response<FamilyJoinResponse>,
                    ) {
                        if (response.isSuccessful){
                            val responseBody = response.body()
                            if (responseBody != null){
                                Toast.makeText(this@QfamilyActivity, "Berhasil Join keluarga", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@QfamilyActivity,MainActivity::class.java)
                                intent.putExtra(EXTRA_NAME3,nameIntro)
                                PopUpYesDialog.hide()
                                startActivity(intent)
                                finish()

                            }
                        } else {
                            Toast.makeText(this@QfamilyActivity, "Token yang anda masukkan salah, coba lagi", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<FamilyJoinResponse>, t: Throwable) {
                        Toast.makeText(this@QfamilyActivity, "gagal menghubungi api", Toast.LENGTH_SHORT).show()
                    }

                })
                progressBar.visibility = View.GONE
            }

        }else{
            Toast.makeText(applicationContext, "Mohon input Token", Toast.LENGTH_SHORT).show()
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

                    qfamilyViewModel.getUser().observe(this@QfamilyActivity){ user ->
                        val client = ApiConfig.getApiService().createFamily("Bearer ${user.idToken}",textInput.toString())
                        client.enqueue(object : Callback<FamilyResponse> {
                            override fun onResponse(
                                call: Call<FamilyResponse>,
                                response: Response<FamilyResponse>,
                            ) {
                                if (response.isSuccessful){
                                    val responseBody = response.body()
                                    if (responseBody != null){
                                        responseBody.data.familyId.let { Log.d("familyId",it) }

                                        Toast.makeText(this@QfamilyActivity, responseBody.message, Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(this@QfamilyActivity, "gagal menambahkan family", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<FamilyResponse>, t: Throwable) {
                                Toast.makeText(this@QfamilyActivity, "gagal menghubungi api", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }

                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra(EXTRA_NAME3,nameIntro)
                    startActivity(intent)
                    finish()

            }else{
                Toast.makeText(applicationContext, "Mohon input text", Toast.LENGTH_SHORT).show()
            }

        }

    }

}