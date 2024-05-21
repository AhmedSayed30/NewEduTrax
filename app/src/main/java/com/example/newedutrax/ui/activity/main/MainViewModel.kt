package com.example.newedutrax.ui.activity.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newedutrax.api.models.PopularCourses
import com.example.newedutrax.ui.fragment.home.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    //    val vm  = HomeViewModel().courseLiveData
    val data = MutableStateFlow(PopularCourses.getPopularCourses())

    fun search(text: String) {
        viewModelScope.launch {
//            data.update {
//                PopularCourses.getPopularCourses().filter {
//                    it.name.contains(text,true)
//                }
//            }


//        Log.e(TAG, vm.value.toString())
//        vm.value?.filter {
//            it.title.contains(text, true)
//        }
        }
    }
}
private const val TAG = "MainViewModel"