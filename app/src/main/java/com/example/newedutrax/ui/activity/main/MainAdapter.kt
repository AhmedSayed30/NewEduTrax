package com.example.newedutrax.ui.activity.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.api.models.PopularCourses
import com.example.newedutrax.databinding.SearchLayoutBinding

class MainAdapter(private val onClick: (item: PopularCourses) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>(){
    private val data: ArrayList<PopularCourses> = ArrayList()

    class ViewHolder(val binding: SearchLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder =
        MainAdapter.ViewHolder(
            SearchLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val item = data[position]
//            courseImg.load(item.image.url)
//            name.text = item.title
//            instNamen.text = item.instructor.name
//            category.text = item.category[0]

            courseImg.setImageResource(item.imgId)
            name.text=item.name
            instNamen.text=item.instName
            category.text=item.category
            root.setOnClickListener {
                onClick(item)
            }
        }
    }
    fun setData(newList: List<PopularCourses>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }
}