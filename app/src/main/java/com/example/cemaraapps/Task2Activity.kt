package com.example.cemaraapps

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.cemaraapps.Api.ApiConfig
import com.example.cemaraapps.databinding.ActivityTask2Binding
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.custom.FirebaseCustomLocalModel
import com.google.firebase.ml.custom.FirebaseCustomRemoteModel
import com.google.firebase.ml.custom.FirebaseModelInterpreter
import com.google.firebase.ml.custom.FirebaseModelInterpreterOptions
import com.google.firebase.ml.modeldownloader.CustomModel
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import org.tensorflow.lite.Interpreter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
class Task2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTask2Binding
    private lateinit var TextInput: EditText
    private lateinit var taskActivityViewModel: TaskActivityViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)
//        setCreateTask()

        var idMember = ""
        taskActivityViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferences.getInstance(dataStore))
        )[taskActivityViewModel::class.java]

    taskActivityViewModel.getUser().observe(this){ user->
        ApiConfig.getApiService().getFamily(user.idToken)
            .enqueue(object : Callback<FamilyGetResponse> {
                override fun onResponse(
                    call: Call<FamilyGetResponse>,
                    response: Response<FamilyGetResponse>,
                ) {
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            val sizeMember = responseBody.data.members.size
                            idMember = responseBody.data.members[sizeMember].id
                        }

                    } else {
                    }
                }

                override fun onFailure(call: Call<FamilyGetResponse>, t: Throwable) {
                    Toast.makeText(this@Task2Activity, "gagal menghubungi api", Toast.LENGTH_SHORT).show()
                }

            })
    }
        
        downloadModel()

        binding.apply {
            backTask.setOnClickListener {
                super.onBackPressed()
            }
            btnAddTask.setOnClickListener {
                Toast.makeText(applicationContext, "Task Berhasil ditambahkan", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
//
//        private fun setCreateTask() {
//            TextInput = findViewById(R.id.etTitle)
//            val textInput = TextInput.text
//            val client = ApiConfig.getApiService().createEvent("Bearer ${idToken}",textInput.toString())
//                .enqueue(object : Callback<CreateEventResponse> {
//                    override fun onResponse(
//                        call: Call<CreateEventResponse>,
//                        response: Response<CreateEventResponse>
//                    ) {
//                        if (response.isSuccessful){
//                            val responseBody = response.body()
//                            if (responseBody != null){
//                                responseBody.data.id.let { Log.d("Id",it) }
//                                responseBody.data.body.let { Log.d("Body", it.toString()) }
//
//                                Toast.makeText(this@Task2Activity, responseBody.message, Toast.LENGTH_SHORT).show()
//                            }
//                        } else {
//                            Toast.makeText(this@Task2Activity, "Gagal menambahkan task", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<CreateEventResponse>, t: Throwable) {
//                        Log.d(ContentValues.TAG, "onFailure: ${t.message.toString()}")
//
//                    }
//
//                })
//    }
//    private fun downloadModel() {
//        val condition = CustomModelDownloadConditions.Builder()
//            .requireWifi()
//            .build()
//        FirebaseModelDownloader.getInstance().getModel("haepe_recommendation",  DownloadType.LOCAL_MODEL, condition)
//            .addOnCompleteListener {
//
//            }
//    download
//    val remoteModel = FirebaseCustomRemoteModel.Builder("haepe_recommendation").build()
//    val conditions = FirebaseModelDownloadConditions.Builder().requireWifi().build()

    //localModel
//    val localModel = FirebaseCustomLocalModel.Builder()
//        .setAssetFilePath("model.tflite")
//        .build()

    //    FirebaseModelManager.getInstance().download(remoteModel, conditions)
//            .addOnCompleteListener{
//                if (it.isSuccessful){
    //certain activity on UI
    //kondisi untuk mengizinkan download.
    //Jika model tidak ada di perangkat, atau jika versi model yang lebih baru tersedia.
    //model dapat didownload jika user sudah menambahkan role dan orang dan waktu
//                }else{
//
//                }
//            }
//    FirebaseModelManager.getInstance().getLatestModelFile(remoteModel)
//        .addOnCompleteListener {
//            if (it.isSuccessful){
//                //tflite model
//                val file = it.result
//            }else{
//                //ui
//            }
//        }
//
//
//    }
    private fun downloadModel() {
        val conditions = CustomModelDownloadConditions.Builder()
            .requireWifi()  // Also possible: .requireCharging() and .requireDeviceIdle()
            .build()
        FirebaseModelDownloader.getInstance()
            .getModel(
                "model.tflite", DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
                conditions
            )
            .addOnSuccessListener { model: CustomModel? ->
                // Download complete. Depending on your app, you could enable the ML
                // feature, or switch from the local model to the remote model, etc.

                // The CustomModel object contains the local path of the model file,
                // which you can use to instantiate a TensorFlow Lite interpreter.
                val modelFile = model?.file
                if (modelFile != null) {
//                    interpreter = Interpreter(modelFile)
                }
            }
        /** Load recommendation candidate list.  */
//    private suspend fun loadCandidateList() {
//        return withContext(Dispatchers.IO) {
//            val collection = MovieRepository.getInstance(context).getContent()
//            for (item in collection) {
//                candidates[item.id] = item
//            }
//            Log.v(TAG, "Candidate list loaded.")
//        }
    }
}
