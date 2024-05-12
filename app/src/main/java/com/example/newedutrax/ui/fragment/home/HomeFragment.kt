package com.example.newedutrax.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.example.newedutrax.ui.activity.course_details.CourseDetailsActivity
import com.example.newedutrax.api.models.Tabs
import com.example.newedutrax.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val coursesAdapter: HomeAdapter by lazy {
        HomeAdapter {
            val intent = Intent(requireActivity(), CourseDetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }
    private val backEndAdapter: HomeAdapter by lazy {
        HomeAdapter {
            val intent = Intent(requireActivity(), CourseDetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }
    private val frontEndAdapter: HomeAdapter by lazy {
        HomeAdapter {
            val intent = Intent(requireActivity(), CourseDetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }
    private val fullStackAdapter: HomeAdapter by lazy {
        HomeAdapter {
            val intent = Intent(requireActivity(), CourseDetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }
    private val cyberAdapter: HomeAdapter by lazy {
        HomeAdapter {
            val intent = Intent(requireActivity(), CourseDetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCourses()

        viewModel.courseLiveData.observe(
            viewLifecycleOwner
        ) {
            coursesAdapter.changeData(it)
            frontEndAdapter.changeData(it.filter { item ->
                item.category.contains("Front End")
            })
            backEndAdapter.changeData(it.filter { item ->
                item.category.contains("Back End")
            })
            fullStackAdapter.changeData(it.filter { item ->
                item.category.contains("Full Stack")
            })
            cyberAdapter.changeData(it.filter { item ->
                item.category.contains("Cyber Security")
            })
        }
        binding.rcCourse.adapter = coursesAdapter
        binding.rcBack.adapter = backEndAdapter
        binding.rcCyber.adapter = cyberAdapter
        binding.rcFront.adapter = frontEndAdapter
        binding.rcFull.adapter = fullStackAdapter
        binding.tvSee.setOnClickListener {

        }
        showTabs(Tabs.getTabs())
    }

    private fun showTabs(tabs: List<Tabs>) {
        tabs.forEach {
            val newTab = binding.tabs.newTab()
            newTab.text = it.name
            binding.tabs.addTab(newTab)
            val layoutParams = LinearLayout.LayoutParams(newTab.view.layoutParams)
            layoutParams.marginEnd = 12
            layoutParams.marginStart = 12
            newTab.view.layoutParams = layoutParams
        }
    }
}