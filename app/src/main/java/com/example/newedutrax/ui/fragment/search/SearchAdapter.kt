package com.example.newedutrax.ui.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newedutrax.api.models.PopularCourses
import com.example.newedutrax.databinding.SearchLayoutBinding

class SearchAdapter(private val onClick: (item: PopularCourses) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val data: ArrayList<PopularCourses> = ArrayList()

    class ViewHolder(val binding: SearchLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        SearchLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val item = data[position]
            ivCourse.load(item.imgId)
            tvCourse.text = item.name
            tvInstructor.text = item.instName

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