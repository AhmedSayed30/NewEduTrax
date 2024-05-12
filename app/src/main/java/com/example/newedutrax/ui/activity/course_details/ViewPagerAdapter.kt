package com.example.newedutrax.ui.activity.course_details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.ui.fragment.instructor.InstructorFragment
import com.example.newedutrax.ui.fragment.overview.OverviewFragment

class ViewPageAdapter(fm: FragmentManager, val item: GetAllCoursesResponseItem?) :
    FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OverviewFragment(item)
            1 -> InstructorFragment(item)
            else -> OverviewFragment(item)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        super.getPageTitle(position)
        return when (position) {
            0 -> "Overview"
            1 -> "Instructor"
            else -> ""
        }
    }
}

