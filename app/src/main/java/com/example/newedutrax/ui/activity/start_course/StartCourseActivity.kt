package com.example.newedutrax.ui.activity.start_course

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.newedutrax.R
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.databinding.ActivityStartCourseBinding
import com.example.newedutrax.ui.activity.course_player.CoursePlayerActivity

class StartCourseActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityStartCourseBinding
    val course: GetAllCoursesResponseItem by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.getParcelableExtra(
            "item",
            GetAllCoursesResponseItem::class.java
        )!!
        else intent.getParcelableExtra("item")!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        binding.apply {
            ivCourse.load(course.image.url)
            tvCourse.text = course.title
            tvInstructor.text = course.instructor.name
            ivBack.setOnClickListener(this@StartCourseActivity)
            btnStart.setOnClickListener(this@StartCourseActivity)
            tvShare.setOnClickListener(this@StartCourseActivity)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.ivBack -> onBackPressed()
            binding.btnStart -> {
                val intent = Intent(binding.root.context, CoursePlayerActivity::class.java)
                intent.putExtra("id", course._id)
                startActivity(intent)            }

            binding.tvShare -> {

            }
        }
    }
}