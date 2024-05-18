package com.example.newedutrax.ui.activity.all_courses

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.newedutrax.R
import com.example.newedutrax.databinding.ActivityAllCoursesBinding
import com.example.newedutrax.ui.activity.course_details.CourseDetailsActivity
import com.example.newedutrax.ui.fragment.home.HomeViewModel
import com.example.newedutrax.ui.fragment.search.SearchAdapter

class AllCoursesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllCoursesBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter: SearchAdapter by lazy {
        SearchAdapter {
            val intent = Intent(this, CourseDetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_courses)
        binding.apply {
            //checkState()
            recyclerView.adapter = adapter
            }
        }


    }
//    private fun checkState() {
//        viewModel.vm.observe(
//            Owner
//        ){
//            adapter.setData(it)
//        }
//
//    }
