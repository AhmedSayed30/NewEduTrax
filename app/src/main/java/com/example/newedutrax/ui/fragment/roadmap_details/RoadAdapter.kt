package com.example.newedutrax.ui.fragment.roadmap_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newedutrax.api.models.Roud
import com.example.newedutrax.databinding.ItemRootBinding

class RoadAdapter(private val items: List<Roud>) :
    RecyclerView.Adapter<RoadAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRootBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder = ViewHolder(
        ItemRootBinding.inflate(
            LayoutInflater.from(p0.context),
            p0,
            false
        )
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        val item = items[position]
        p0.binding.title.text = item.title
        p0.binding.description.text = item.description
    }
}