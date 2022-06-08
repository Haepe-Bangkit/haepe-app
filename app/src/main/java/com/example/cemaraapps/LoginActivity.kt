package com.example.cemaraapps


import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    companion object {
        const val RC_SIGN_IN = 123
        const val EXTRA_NAME = "EXTRA_NAME"
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("339240129870-fkefjuqt2uhbb3dj3j7ig9aaodeodj8g.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnSignIn.visibility = View.VISIBLE
        binding.btnSignIn.setOnClickListener {
            val signInIntent = mGoogleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            binding.btnSignIn.visibility = View.VISIBLE
        }


    }

    private fun updateUI(currentUser: GoogleSignInAccount?) {
        if (currentUser != null) {
            val intent = Intent(applicationContext, IntroActivity::class.java)
            intent.putExtra(EXTRA_NAME, currentUser.displayName)
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
//
//    private fun setLogin(
//        code: String,
//        client_id: String,
//        client_secret: String,
//        grant_type: String,
//        redirect_uri: String
//    ) {
//        ApiConfig.getApiService().getLogin(code, client_id, client_secret, grant_type, redirect_uri)
//            .enqueue(object : Callback<LoginResponse> {
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val user = response.body()
//                        user!!.access_token.let { Log.e("access_token", it) }
//                        user!!.client_id?.let { Log.e("id", it) }
//                        user!!.code?.let { Log.e("code", it) }
//                        user!!.client_secret.let { Log.e("client_sec", it) }
//                        user!!.grant_type.let { Log.d("grant", it) }
//                        user!!.redirect_uri.let { Log.d("uri", it) }
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
//                }
//
//            })
//    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            val idToken = account.idToken
            Log.d("idToken", idToken.toString())
            updateUI(account)

        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode())
            updateUI(null)
        }
    }
}