package com.example.newedutrax.ui.fragment.roadmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newedutrax.R
import com.example.newedutrax.ui.fragment.roadmap_details.RoadmapDetailsFragment
import com.example.newedutrax.api.models.Trax
import com.example.newedutrax.databinding.FragmentRoadMapBinding

class RoadMapFragment : Fragment() {
    private lateinit var binding: FragmentRoadMapBinding
    var adapter = RoadMapAdapter(Trax.getTracsList()) {

        val bundle = Bundle()
        bundle.putString("id", it.id)
        bundle.putString("title",it.name)
        bundle.putInt("image",it.imagId)

        val fragInfo = RoadmapDetailsFragment()
        fragInfo.setArguments(bundle)

        activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container_fragment, fragInfo)
            ?.addToBackStack("null")
            ?.commit()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoadMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcMaps.adapter = adapter

    }

}
