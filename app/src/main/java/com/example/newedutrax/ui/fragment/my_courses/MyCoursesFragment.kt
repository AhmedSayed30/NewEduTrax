package com.example.newedutrax.ui.fragment.my_courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newedutrax.databinding.FragmentMyCoursesBinding
import com.example.newedutrax.utils.SharedPrefUtils


class MyCoursesFragment : Fragment() {
    private lateinit var binding: FragmentMyCoursesBinding
    val viewModel: MyCoursesViewModel by viewModels()
    private val adapter : MyCoursesAdapter by lazy {
        MyCoursesAdapter{

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyCoursesBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMyCourses(SharedPrefUtils.getToken(binding.root.context),
            SharedPrefUtils.getId(binding.root.context)
        )
        viewModel.courseLiveData.observe(
            viewLifecycleOwner
        ){
            //adapter.setData(it)
        }
        binding.MyCourses.adapter = adapter

    }

}