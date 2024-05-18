package com.example.newedutrax.ui.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newedutrax.api.models.PopularCourses
import com.example.newedutrax.ui.fragment.home.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val vm  = HomeViewModel().courseLiveData
    //val data = //MutableStateFlow(PopularCourses.getPopularCourses())
    fun search(text: String) {
        vm.value?.filter {
            it.title.contains(text,true)
        }
//        vm.value?.filter {
//            it.category.contains(text)
//        }
//        vm.value?.filter {
//            it.instructor.name.contains(text)
//        }
//        viewModelScope.launch {
//            data.update {
//                PopularCourses.getPopularCourses().filter {
//                    it.name.contains(text,true)
//                }
//            }
//            data.update {
//                PopularCourses.getPopularCourses().filter {
//                    it.instName.contains(text,true)
//                }
//            }
//            data.update {
//                PopularCourses.getPopularCourses().filter {
//                    it.category.contains(text,true)
//                }
//            }
//        }
    }
}