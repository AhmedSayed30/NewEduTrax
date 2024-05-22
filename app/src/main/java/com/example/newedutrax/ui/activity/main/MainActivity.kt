package com.example.newedutrax.ui.activity.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
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
//        binding.icAccount.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

    return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val manager=getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = item.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()

                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
//            if (newText != null) {
//                viewModel.search(newText)
//            }
                Toast.makeText(this@MainActivity, newText, Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return super.onOptionsItemSelected(item)
    }
    private fun checkState() {
        adapter.setData(viewModel.data.value)
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                it?.let {
                    adapter.setData(it)
                }
            }
        }

//        viewModel.vm.observe(
//            this
//        ) {
//            adapter.setData(it)
//        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }
}


