package com.example.newedutrax.ui.activity.register

import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.newedutrax.utils.NetworkUtils.createJsonRequestBody
import com.example.newedutrax.api.ApiManager
import com.example.newedutrax.api.models.AuthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    var navigator: Navigator? = null
    val userErrorMessage = ObservableField<String?>()
    val emailErrorMessage = ObservableField<String?>()
    val passwordErrorMessage = ObservableField<String?>()
    val userNumber = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    fun register() {
        if (validForm()) {
            navigator?.showlading("Loading...")

            val body = createJsonRequestBody(
                "name" to userNumber.get().toString(),
                "email" to email.get().toString(),
                "password" to password.get().toString()
            )


            ApiManager.getApis()
                .register(body)
                .enqueue(object : Callback<AuthResponse> {
                    override fun onResponse(p0: Call<AuthResponse>, p1: Response<AuthResponse>) {
                        if (p1.isSuccessful) {
                            navigator?.hideDialoge()
                            navigator?.showToast(p1.message())
                            navigator?.bacK(p1.body())
                            navigator?.goVerify(email.get().toString())
                        }
                    }

                    override fun onFailure(p0: Call<AuthResponse>, p1: Throwable) {
                        navigator?.hideDialoge()
                        p1.localizedMessage?.let { navigator?.showMassage(it) }
                        Log.e(TAG, p1.message.toString())
                    }
                })
        }
    }

    private fun validForm(): Boolean {
        if (userNumber.get().isNullOrBlank()) {
            userErrorMessage.set("Empty Field")
            return false
        } else userErrorMessage.set(null)

        if (email.get().isNullOrBlank()) {
            emailErrorMessage.set("Empty Fieald")
            return false
        } else if (!EMAIL_ADDRESS.matcher(email.get().toString()).matches()) {
            emailErrorMessage.set("Enter valied e-mail")
            return false
        } else emailErrorMessage.set(null)

        if (password.get().isNullOrBlank()) {
            passwordErrorMessage.set("Empty Fieald")
            return false
        } else if (password.get()?.length!! < 6) {
            passwordErrorMessage.set("The password should be more than 6 digits")
            return false
        } else if (!password.get().toString().contains("[a-z]".toRegex())) {
            passwordErrorMessage.set("The password should should contain lower case")
            return false
        } else passwordErrorMessage.set(null)

        return true
    }
}

private const val TAG = "RegisterViewModel"