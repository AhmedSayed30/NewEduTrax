package com.example.newedutrax.ui.activity.course_player

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newedutrax.api.ApiManager
import com.example.newedutrax.api.models.CourseLecResponse
import com.example.newedutrax.api.models.CourseLecResponseItem
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CoursePlayerViewModel {
    private val _state = MutableStateFlow(CoursePlayerViewModel.CourseLecState())
    val state = _state.asSharedFlow()
    private val _data: MutableStateFlow<CourseLecResponse?> = MutableStateFlow(null)
    var data = MutableLiveData<List<CourseLecResponseItem>>()
    fun getCourseLec(token: String, courseId: String){
        _state.update { it.copy(isLoading = true) }
        ApiManager.getApis().getAllLec(token,courseId)
            .enqueue(object :Callback<CourseLecResponse>{
                override fun onResponse(
                    p0: Call<CourseLecResponse>,
                    p1: Response<CourseLecResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    Log.i(TAG, p1.body().toString())
                    Log.i(TAG, p1.errorBody().toString())
                    Log.i(TAG, p1.message().toString())
                    Log.i(TAG, "Is Successfull : ${p1.isSuccessful}")

                    if (p1.isSuccessful) {
                        //_data.update { p1.body()}
                        data.value=p1.body()
                    }
                }

                override fun onFailure(p0: Call<CourseLecResponse>, p1: Throwable) {
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
data class CourseLecState(
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
private const val TAG = "CoursePlayerViewModel"