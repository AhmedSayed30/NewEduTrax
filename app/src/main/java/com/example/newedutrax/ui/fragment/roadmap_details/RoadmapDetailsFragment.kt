package com.example.newedutrax.ui.fragment.roadmap_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newedutrax.api.models.Roud
import com.example.newedutrax.databinding.FragmentRoadBinding

class RoadmapDetailsFragment : Fragment() {
    private lateinit var binding: FragmentRoadBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoadBinding.inflate(inflater, container, false)
        Roud.initData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("id").toString()
        binding.title.setText(arguments?.getString("title"))
        arguments?.getInt("image")?.let { binding.tracImg.setImageResource(it) }
        val adapter = RoadAdapter(Roud.round[id] ?: emptyList())
        binding.rcRoad.adapter = adapter
    }
}