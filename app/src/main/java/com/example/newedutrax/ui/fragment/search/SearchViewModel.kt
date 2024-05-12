package com.example.newedutrax.ui.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newedutrax.api.models.PopularCourses
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val data = MutableStateFlow(PopularCourses.getPopularCourses())
    fun search(text: String) {
        viewModelScope.launch {
            data.update {
                PopularCourses.getPopularCourses().filter {
                    it.name.contains(text,true)
                }
            }
        }
    }
}