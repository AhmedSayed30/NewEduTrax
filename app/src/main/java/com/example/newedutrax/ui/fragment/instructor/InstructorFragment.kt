package com.example.newedutrax.ui.fragment.instructor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.databinding.FragmentInstructorBinding


class InstructorFragment(val item: GetAllCoursesResponseItem?) : Fragment() {
    private lateinit var binding: FragmentInstructorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstructorBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            instAbout.text = item?.instructor?.about
            instImag.load(item?.instructor?.avatar?.url)
            instName.text=item?.instructor?.name
            instTitle.text=item?.instructor?.title
        }
    }


}