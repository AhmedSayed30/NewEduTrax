package com.example.newedutrax.ui.activity.all_courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.databinding.SearchLayoutBinding

class AllCoursesAdapter (private val onClick: (item: GetAllCoursesResponseItem) -> Unit) :
    RecyclerView.Adapter<AllCoursesAdapter.ViewHolder>() {
    private val data: ArrayList<GetAllCoursesResponseItem> = ArrayList()

    class ViewHolder(val binding: SearchLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        SearchLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val item = data[position]
            courseImg.load(item.image.url)
            name.text = item.title
            instNamen.text = item.instructor.name
            category.text = item.category[0]

            root.setOnClickListener {
                onClick(item)
            }
        }
    }

    fun setData(newList: List<GetAllCoursesResponseItem>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }
}