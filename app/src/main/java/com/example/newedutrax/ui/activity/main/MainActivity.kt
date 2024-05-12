package com.example.newedutrax.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newedutrax.ui.fragment.more.MoreFragment
import com.example.newedutrax.ui.fragment.quiz.QuizFragment
import com.example.newedutrax.R
import com.example.newedutrax.ui.fragment.roadmap.RoadMapFragment
import com.example.newedutrax.ui.fragment.search.SearchFragment
import com.example.newedutrax.databinding.ActivityMainBinding
import com.example.newedutrax.ui.activity.login.LoginActivity
import com.example.newedutrax.ui.fragment.home.HomeFragment
import com.example.newedutrax.utils.SharedPrefUtils
import com.example.newedutrax.utils.SharedPrefUtils.getToken
import com.example.newedutrax.utils.SharedPrefUtils.isUserLogged


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (isUserLogged(this)) {
            binding.icAccount.visibility = View.GONE
        }
        binding.btnNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> showFragment(HomeFragment())
                R.id.search -> showFragment(SearchFragment())
                R.id.road_map -> showFragment(RoadMapFragment())
                R.id.quizes -> showFragment(QuizFragment())
                R.id.more -> showFragment(MoreFragment())
            }
            return@setOnItemSelectedListener true
        }
        binding.btnNavigationView.selectedItemId = R.id.home
        binding.icAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }
}


