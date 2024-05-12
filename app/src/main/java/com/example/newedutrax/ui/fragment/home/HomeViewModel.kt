package com.example.newedutrax.ui.fragment.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newedutrax.api.ApiManager
import com.example.newedutrax.api.models.GetAllCoursesResponse
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    var courseLiveData= MutableLiveData<List<GetAllCoursesResponseItem>>()
    fun getCourses(){
        ApiManager.getApis().getAllCourses().enqueue(object :Callback<GetAllCoursesResponse>{
            override fun onResponse(
                p0: Call<GetAllCoursesResponse>,
                p1: Response<GetAllCoursesResponse>
            ) {
                if(p1.isSuccessful){
                    courseLiveData.value=p1.body()
                }
            }

            override fun onFailure(p0: Call<GetAllCoursesResponse>, p1: Throwable) {
//                Toast.makeText(this@HomeFragment.context
//                  ,"Some thing wrong please try again later!"
//                ,Toast.LENGTH_LONG).show()
            }

        })
    }
}
private const val TAG = "HomeViewModel"