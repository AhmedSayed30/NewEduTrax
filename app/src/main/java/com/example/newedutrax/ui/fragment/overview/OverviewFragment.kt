package com.example.newedutrax.ui.fragment.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.databinding.FragmentOverviewBinding

class OverviewFragment(val item: GetAllCoursesResponseItem?) : Fragment() {
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvDesc.text = item?.description
            tvLearm.text = item?.willLearn
            tvRequ.text = item?.requirement?.r1 + "\n" + item?.requirement?.r2
        }
    }
}