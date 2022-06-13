package com.example.antrisehat.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrisehat.data.model.UserModel
import com.example.antrisehat.data.model.UserPreference
import com.example.antrisehat.helper.Helper
import com.example.antrisehat.data.remote.response.LoginResponse
import com.example.antrisehat.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginrsViewModel(private val pref: UserPreference) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun login(email: String, pass: String, callback: Helper.ApiCallbackString){
        _isLoading.value = true

        val service = ApiConfig().getApiService().login(email, pass)
        service.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {

                        callback.onResponse(response.body() != null, SUCCESS)

                        val model = UserModel(
                            responseBody.loginResult.name,
                            email,
                            pass,
                            responseBody.loginResult.userId,
                            responseBody.loginResult.token,
                            true,
                            responseBody.loginResult.nik,
                            responseBody.loginResult.noTelp,
                            responseBody.loginResult.tglLahir,
                            responseBody.loginResult.jensKelamin
                        )
                        saveUser(model)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")

                    // get message error
                    val jsonObject = JSONTokener(response.errorBody()!!.string()).nextValue() as JSONObject
                    val message = jsonObject.getString("message")
                    callback.onResponse(false, message)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                callback.onResponse(false, t.message.toString())
            }
        })
    }

    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }

    companion object {
        private const val TAG = "SignInViewModel"
        private const val SUCCESS = "success"
    }
}