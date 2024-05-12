package com.example.newedutrax.ui.activity.login

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.newedutrax.api.ApiManager
import com.example.newedutrax.api.models.LogResponse
import com.example.newedutrax.ui.activity.register.Navigator
import com.example.newedutrax.utils.NetworkUtils.createJsonRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    var navigator: NavigatorLog? = null
    val emailErrorMessage = ObservableField<String?>()
    val passwordErrorMessage = ObservableField<String?>()
    val errorMassage = ObservableField<String?>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()

    fun logIn() {
        if (validForm()) {
            navigator?.showlading("Loading...")

            val body = createJsonRequestBody(
                "email" to email.get().toString(),
                "password" to password.get().toString()
            )
//            val jsonObject = JSONObject()
//            jsonObject.put("email", email.get().toString())
//            jsonObject.put("password", password.get().toString())
//
//            val body = RequestBody.create(
//                "application/json; charset=utf-8".toMediaTypeOrNull(),
//                jsonObject.toString()
//            )

            ApiManager.getApis()
                .login(body)
                .enqueue(object : Callback<LogResponse> {
                    override fun onResponse(p0: Call<LogResponse>, p1: Response<LogResponse>) {
                        Log.i(TAG, p1.body().toString())
                        Log.i(TAG, p1.errorBody().toString())
                        Log.i(TAG, p1.message().toString())
                        Log.i(TAG, "Is Successfull : ${p1.isSuccessful}")
                        if (p1.isSuccessful) {
                            navigator?.hideDialoge()
                            navigator?.showToast(p1.message())
                            navigator?.bacK(p1.body())
                            navigator?.goHome()
                        } else {
                            navigator?.showMassage(p1.message())
                            Log.e(TAG, p1.message())
                        }
                    }

                    override fun onFailure(p0: Call<LogResponse>, p1: Throwable) {
                        navigator?.hideDialoge()
                        p1.message?.let { navigator?.showMassage(it) }
                        Log.e(TAG, p1.message.toString())

                    }
                })
        }
    }

    fun validForm(): Boolean {
        if (email.get().isNullOrBlank()) {
            emailErrorMessage.set("Empty Fieald")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.get().toString()).matches()) {
            emailErrorMessage.set("Enter valied e-mail")
            return false
        } else emailErrorMessage.set(null)

        if (password.get().isNullOrBlank()) {
            passwordErrorMessage.set("Empty Fieald")
            return false
        } else if (password.get()?.length!! < 6) {
            passwordErrorMessage.set("The password should be more than 6 digits")
            return false
        } else passwordErrorMessage.set(null)

        return true
    }
}

private const val TAG = "LogViewModel"