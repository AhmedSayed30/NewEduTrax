package com.example.newedutrax.ui.fragment.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.newedutrax.api.models.GetAllCoursesResponse
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.databinding.ItemPopulareCoursesBinding

class HomeAdapter(private val onClick: (item: GetAllCoursesResponseItem) -> Unit) :
    Adapter<HomeAdapter.ViewHolder>() {
    private val data = ArrayList<GetAllCoursesResponseItem>()

    class ViewHolder(val binding: ItemPopulareCoursesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder = ViewHolder(
        ItemPopulareCoursesBinding.inflate(
            LayoutInflater.from(p0.context),
            p0,
            false
        )
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.name.text = item.title
        holder.binding.category.text = item.category[0]
        holder.binding.hours.text = item.hours + "h"
        holder.binding.courseImg.load(item.image.url)
        holder.binding.lesson.text = item.lessons
        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }

    fun changeData(newList: List<GetAllCoursesResponseItem>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()

    }

}

private const val TAG = "HomeAdapter"