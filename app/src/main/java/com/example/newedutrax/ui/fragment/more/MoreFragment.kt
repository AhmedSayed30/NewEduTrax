package com.example.newedutrax.ui.fragment.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newedutrax.databinding.FragmentMoreBinding
import com.example.newedutrax.ui.activity.course_details.CourseDetailsActivity
import com.example.newedutrax.ui.activity.main.MainActivity
import com.example.newedutrax.utils.SharedPrefUtils.clearUserData
import com.example.newedutrax.utils.SharedPrefUtils.getName

class MoreFragment : Fragment() {
    private lateinit var binding: FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvName.text = getName(binding.root.context)
            btnLogout.setOnClickListener {
                clearUserData(binding.root.context)
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }
        binding.icFace.setOnClickListener {
            openApp("https://www.facebook.com/saiddfa?mibextid=JRoKGi")
        }
        binding.icInsta.setOnClickListener {
            openApp("https://www.instagram.com/ahmedsayed5081?igsh=djAxeHdqaWl6ajVx")
        }
        binding.icTwitt.setOnClickListener {
            openApp("https://twitter.com/as3461074?t=cpw3Teg-D_L5q7sswWKe1g&s=09")
        }

    }

    private fun openApp(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        requireContext().startActivity(intent)
    }
}