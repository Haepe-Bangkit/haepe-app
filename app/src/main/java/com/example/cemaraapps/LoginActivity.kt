package com.example.cemaraapps


import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.databinding.ActivityLoginBinding
import com.example.cemaraapps.model.DataFamily
import com.example.cemaraapps.model.DataUser
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch
import org.chromium.base.Promise
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
class LoginActivity : AppCompatActivity() {
    companion object {
        const val RC_SIGN_IN = 123
        const val EXTRA_NAME = "EXTRA_NAME"
    }
    private val splashTimeOut: Long = 2000
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[LoginViewModel::class.java]

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("339240129870-fkefjuqt2uhbb3dj3j7ig9aaodeodj8g.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

            binding.btnSignIn.setOnClickListener {
                binding.progressBarLogin.visibility = View.VISIBLE
                val signInIntent = mGoogleSignInClient.getSignInIntent()
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }

    }


    private fun updateUI(currentUser: GoogleSignInAccount?) {
        if (currentUser != null) {
            val intent = Intent(this,IntroActivity::class.java)
            intent.putExtra(EXTRA_NAME,currentUser.displayName)
            startActivity(intent)
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            val idTokenAuth = account.idToken.toString()
            Log.d("idTokenGoogleAuth", idTokenAuth)
            loginViewModel.setLogin(idTokenAuth) {
                checkFamily{
                updateUI(account)
                }
            }


        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code = " + e.statusCode)
            updateUI(null)
        }
    }
    private fun checkFamily(callback: () -> Unit){

        loginViewModel.getUser().observe(this){user->

        ApiConfig.getApiService().getFamily(user.idToken )
            .enqueue(object : Callback<FamilyGetResponse> {
            override fun onResponse(
                call: Call<FamilyGetResponse>,
                response: Response<FamilyGetResponse>
            ) {
                if (response.isSuccessful) {
                    val family = response.body()
                    if(family!=null&&family.status=="success"){

                        family.data.members
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    }
                        callback.invoke()
                }
            }

            override fun onFailure(call: Call<FamilyGetResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }
        })
        }
    }
}