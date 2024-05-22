package com.example.newedutrax.ui.fragment.my_courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newedutrax.api.models.EnrolledCourse
import com.example.newedutrax.databinding.SearchLayoutBinding

class MyCoursesAdapter(private val onClick: (item: EnrolledCourse) -> Unit):
    RecyclerView.Adapter<MyCoursesAdapter.ViewHolder>(){
    private val data: ArrayList<EnrolledCourse> = ArrayList()

    class ViewHolder(val binding: SearchLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SearchLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val item = data[position]
            courseImg.load(item.image.url)
            name.text = item.title
            instNamen.text = item.instructor
            category.text = item.type

            root.setOnClickListener {
                onClick(item)
            }
        }    }
    fun setData(newList: List<EnrolledCourse>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }
}