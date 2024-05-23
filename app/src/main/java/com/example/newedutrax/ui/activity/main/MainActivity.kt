package com.example.newedutrax.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newedutrax.ui.fragment.more.MoreFragment
import com.example.newedutrax.ui.fragment.quiz.QuizFragment
import com.example.newedutrax.R
import com.example.newedutrax.ui.fragment.roadmap.RoadMapFragment
import com.example.newedutrax.databinding.ActivityMainBinding
import com.example.newedutrax.ui.fragment.home.HomeFragment
import com.example.newedutrax.utils.SharedPrefUtils.isUserLogged
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.newedutrax.ui.activity.course_details.CourseDetailsActivity
import com.example.newedutrax.ui.fragment.my_courses.MyCoursesFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val adapter: MainAdapter by lazy {
        MainAdapter {
            val intent = Intent(this, CourseDetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (isUserLogged(this)) {
            binding.icAccount.visibility = View.GONE
        }

        checkState()

//        binding.rc.adapter = adapter
        binding.btnNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> showFragment(HomeFragment())
                R.id.my_courses -> showFragment(MyCoursesFragment())
                R.id.road_map -> showFragment(RoadMapFragment())
                R.id.quizes -> showFragment(QuizFragment())
                R.id.more -> showFragment(MoreFragment())
            }
            return@setOnItemSelectedListener true
        }
        binding.btnNavigationView.selectedItemId = R.id.home
        binding.ivSearch.setOnClickListener {
            showSearchView()
        }
        binding.ivBack.setOnClickListener {
            hideSearchView()
        }
        binding.searchView.doOnTextChanged { text, start, before, count ->
            Log.i(TAG, text.toString())
        }
//        binding.icAccount.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
    }

    private fun checkState() {
        adapter.setData(viewModel.data.value)
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                it.let {
                    adapter.setData(it)
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }

    private fun showSearchView() {
        binding.apply {
            ivLogo.isVisible = false
            ivSearch.isVisible = false
            icAccount.isVisible = false
            searchView.isVisible = true
            ivBack.isVisible = true

        }
    }

    private fun hideSearchView() {
        binding.apply {
            ivLogo.isVisible = true
            ivSearch.isVisible = true
            icAccount.isVisible = true
            searchView.isVisible = false
            ivBack.isVisible = false
        }

    }
}

private const val TAG = "MainActivity"