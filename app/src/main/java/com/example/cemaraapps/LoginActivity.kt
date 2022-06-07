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
    //firebase auth
//    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        firebaseAuth = Firebase.auth


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
    private fun setLogin(code: String, client_id: String, client_secret: String, grant_type:String, redirect_uri: String){
        ApiConfig.getApiService().getLogin(code, client_id, client_secret,grant_type,redirect_uri)
            .enqueue(object: Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful){
                        val user = response.body()
                        user!!.access_token.let { Log.e("access_token",it) }
                        user!!.client_id?.let { Log.e("id", it) }
                        user!!.code?.let { Log.e("code", it) }
                        user!!.client_secret.let { Log.e("client_sec",it) }
                        user!!.grant_type.let { Log.d("grant",it) }
                        user!!.redirect_uri.let{Log.d("uri",it)}
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }

            })
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            val idToken = account.idToken
            Log.d("idToken",idToken.toString())
            //firebase auth
//            Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
//            firebaseAuthWithGoogle(account.idToken!!)
//            Log.d("account","s")
//            setLogin("eyJhbGciOiJSUzI1NiIsImtpZCI6Ijc0ODNhMDg4ZDRmZmMwMDYwOWYwZTIyZjNjMjJkYTVmZTM5MDZjY2MiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIzMzkyNDAxMjk4NzAtOWFoaGtmZnRkMmVqOGU2aWpzZ2ZnMWxtNG5wdGh1NmkuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIzMzkyNDAxMjk4NzAtZmtlZmp1cXQydWhiYjNkajNqN2lnOWFhb2Rlb2RqOGcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTgyNzg2NDkwNzE5MDExMDI2MzIiLCJlbWFpbCI6InJpZGhvYXFsaWVAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJSaWRobyBBcWxpIEVmZW5kaSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS0vQU9oMTRHaHVhcDJOUUxXSTdqM2JPakpGclp5aVI1TEl3bHBPY2R2RHM4YmNxQT1zOTYtYyIsImdpdmVuX25hbWUiOiJSaWRobyIsImZhbWlseV9uYW1lIjoiQXFsaSBFZmVuZGkiLCJsb2NhbGUiOiJlbiIsImlhdCI6MTY1NDQxMjA2OSwiZXhwIjoxNjU0NDE1NjY5fQ.OyrTXgcwY2pdkLqGy4O6gvar-fu2qpDyd5rY2LTuRzbr1PwWVbQniaurFwTIkHWv40GWKkso5GRMLN9Oq6ElPpbZC7c-fIt3naPs4nlB0GDV0bmUPH2piQ4FikfiAXbkBq1ph4b-HuWLSNK_McWQ0iwYx2o-U9yxm_sHHVJDPj02CXRs9aE7b4euBNvL1buOvZMYqBtOg36g98RvW8eYcMykQfAcSL5Eq_tE_Is_EknbMK2nLzhysTPAbUfVIFSQRhpgU9F47hUSJht1QcwXt-Z5kTVQtvg94F0Cd53jHmAoVfr633UxoLlj-ouai4ykpBiLv33n_P-yjELMh5y8SQ"
//                ,"728807852869-qcjv3s5qptuav3d02otuu88dtjsf83t5.apps.googleusercontent.com",
//                "728807852869-qcjv3s5qptuav3d02otuu88dtjsf83t5.apps.googleusercontent.com",
//                "authorization_code",
//                "http://localhost:8080")
//            val httpClient: HttpClient = DefaultHttpClient()
//            val httpPost = HttpPost("https://cemaraapps.uc.r.appspot.com/auth/login")
//
//            try {
//                val nameValuePairs: MutableList<NameValuePair> = ArrayList<NameValuePair>(1)
//                nameValuePairs.add(BasicNameValuePair("idToken", idToken))
//                httpPost.setEntity(UrlEncodedFormEntity(nameValuePairs))
//                val response: java.net.HttpResponse = httpClient.execute(httpPost)
//                val statusCode: Int = response.getStatusLine().getStatusCode()
//                val responseBody: String = EntityUtils.toString(response.getEntity())
//                Log.i(TAG, "Signed in as: $responseBody")
//
//            } catch (e: ClientProtocolException) {
//                Log.e(TAG, "Error sending ID token to backend.", e)
//            } catch (e: IOException) {
//                Log.e(TAG, "Error sending ID token to backend.", e)
//            }
//
            updateUI(account)

        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode())
//            updateUI(null)
        }
    }
    //firebase auth
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        firebaseAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(ContentValues.TAG, "signInWithCredential:success")
//                    val user = firebaseAuth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
////                  updateUI(null)
//                }
//            }
//    }
//    private fun updateUI(currentUser: FirebaseUser?) {
//        if (currentUser != null) {
//            val intent = Intent(applicationContext, MainActivity::class.java)
////            intent.putExtra(EXTRA_NAME, currentUser.displayName)
//            startActivity(intent)
//            finish()
//        }
//
//    }

}
