package com.example.newedutrax.ui.fragment.roadmap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.newedutrax.api.models.Trax
import com.example.newedutrax.databinding.ItemMapBinding

class RoadMapAdapter (val items: List<Trax>, val onClick: (item: Trax)->Unit)

    :Adapter<RoadMapAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemMapBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMapBinding
            .inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.mapsImg.setImageResource(items[position].imagId)
        holder.binding.mapsName.setText(items[position].name)
        holder.binding.root.setOnClickListener{
            onClick (items[position])
        }

    }
}