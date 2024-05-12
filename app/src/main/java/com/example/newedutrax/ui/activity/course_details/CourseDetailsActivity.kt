package com.example.newedutrax.ui.activity.course_details

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.newedutrax.R
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.databinding.FragmentCourseDetalBinding
import com.example.newedutrax.ui.activity.start_course.StartCourseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CourseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: FragmentCourseDetalBinding
    private val viewModel: CourseDetailsViewModel by viewModels()
    val item: GetAllCoursesResponseItem by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.getParcelableExtra(
            "item",
            GetAllCoursesResponseItem::class.java
        )!!
        else intent.getParcelableExtra("item")!!
    }
    val adapter: ViewPageAdapter by lazy {
        ViewPageAdapter(supportFragmentManager, item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_course_detal)
        binding.name.text = item.title
        binding.ivMentor.load(item.instructor.avatar.url)
        binding.tvMentorName.text = item.instructor.name
        binding.pager.adapter = adapter
        binding.tabView.setupWithViewPager(binding.pager)
        binding.btnEnroll.setOnClickListener {
            // viewModel.enroll(getToken(binding.root.context), item._id)
            val intent = Intent(binding.root.context, StartCourseActivity::class.java)
            intent.putExtra("course", item)
            startActivity(intent)
        }
        checkState()
    }

    private fun checkState() {
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                it?.let {
                    val intent = Intent(binding.root.context, StartCourseActivity::class.java)
                    intent.putExtra("course", item)
                    startActivity(intent)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                if (it.error?.isNotEmpty() == true) Toast.makeText(
                    binding.root.context,
                    it.error.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}