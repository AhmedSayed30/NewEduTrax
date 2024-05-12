package com.example.newedutrax.ui.activity.course_details

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.newedutrax.api.ApiManager
import com.example.newedutrax.api.models.EnrollCourseResponse
import com.example.newedutrax.api.models.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseDetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(CourseDetailsState())
    val state = _state.asSharedFlow()
    private val _data: MutableStateFlow<EnrollCourseResponse?> = MutableStateFlow(null)
    val data = _data.asStateFlow()

    fun enroll(token: String, courseId: String) {
        _state.update { it.copy(isLoading = true) }
        ApiManager.getApis().enrollCourse(token, courseId)
            .enqueue(object : Callback<EnrollCourseResponse> {
                override fun onResponse(
                    p0: Call<EnrollCourseResponse>,
                    response: Response<EnrollCourseResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    if (response.isSuccessful) {
                        _data.update { response.body() }
                    } else {
                        try {
                            val error: ErrorResponse = Gson().fromJson(
                                response.errorBody()?.charStream(),
                                ErrorResponse::class.java
                            )
                            _state.update { it.copy(error = error.message) }
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }
                    }
                }

                override fun onFailure(p0: Call<EnrollCourseResponse>, p1: Throwable) {
                    Log.e(TAG, p1.message.toString())
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = p1.message
                        )
                    }
                }
            })
    }

    data class CourseDetailsState(
        val isLoading: Boolean = false,
        val error: String? = null
    )
}

private const val TAG = "CourseDetailsViewModel"