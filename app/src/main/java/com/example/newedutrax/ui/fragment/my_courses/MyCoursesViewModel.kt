package com.example.newedutrax.ui.fragment.my_courses

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newedutrax.api.ApiManager
import com.example.newedutrax.api.models.AllMyCoursesResponse
import com.example.newedutrax.api.models.EnrolledCourse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCoursesViewModel : ViewModel() {
    var courseLiveData= MutableLiveData<AllMyCoursesResponse>()
    fun getMyCourses(token: String,userId: String){
        ApiManager.getApis().getMyCourses(token,userId)
            .enqueue(object : Callback<AllMyCoursesResponse>{
                override fun onResponse(
                    p0: Call<AllMyCoursesResponse>,
                    p1: Response<AllMyCoursesResponse>
                ) {
                    Log.i(TAG, p1.body().toString())
                    Log.i(TAG, p1.errorBody().toString())
                    Log.i(TAG, p1.message().toString())
                    Log.i(TAG, "Is Successfull : ${p1.isSuccessful}")
                    if(p1.isSuccessful){
//                        courseLiveData.value=p1.body()
                    }
                }

                override fun onFailure(p0: Call<AllMyCoursesResponse>, p1: Throwable) {
                    Log.e(TAG, p1.message.toString())
                }


            })
    }
}
private const val TAG = "MyCoursesViewModel"
