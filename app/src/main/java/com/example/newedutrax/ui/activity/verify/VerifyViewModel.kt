package com.example.newedutrax.ui.activity.verify

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.newedutrax.api.ApiManager
import com.example.newedutrax.api.models.verifyResponse
import com.example.newedutrax.ui.activity.login.NavigatorLog
import com.example.newedutrax.utils.NetworkUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifyViewModel : ViewModel(){
    var navigator: NavigatorVer? = null

    private val _state = MutableStateFlow(verifyState())
    val state = _state.asSharedFlow()
    val verify = ObservableField<String>()
    val verifyErrorMessage = ObservableField<String?>()


    fun verify(email: String){
        _state.update { it.copy(isLoading = true) }
        if(validForm()){
            val body = NetworkUtils.createJsonRequestBody(
                "email" to email.toString(),
                "verify" to verify.get().toString(),
            )
            ApiManager.getApis()
                .verifyAccount(body)
                .enqueue(object : Callback<verifyResponse> {
                    override fun onResponse(
                        p0: Call<verifyResponse>,
                        p1: Response<verifyResponse>
                    ) {
                        _state.update { it.copy(isLoading = false) }
                        Log.i(TAG, p1.body().toString())
                        Log.i(TAG, p1.errorBody().toString())
                        Log.i(TAG, p1.message().toString())
                        if (p1.isSuccessful) {
                            navigator?.goHome()
                            _state.update { it.copy(massage = p1.message()) }

                        }
                    }

                    override fun onFailure(p0: Call<verifyResponse>, p1: Throwable) {
                        p1.message?.let { navigator?.showToast(it) }
                        _state.update { it.copy(
                            isLoading = false,
                            massage = p1.message) }
                    }

                })

        }
    }
    private fun validForm(): Boolean {
        if (verify.get().isNullOrBlank()) {
            verifyErrorMessage.set("Empty Field")
            return false
        } else verifyErrorMessage.set(null)
        return true
    }
    data class verifyState(
        val isLoading: Boolean = false,
        val massage: String? = null
    )
}


private const val TAG = "verifyViewModel"