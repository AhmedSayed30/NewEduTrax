package com.example.newedutrax.ui.activity.course_player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.newedutrax.api.models.CourseLecResponseItem
import com.example.newedutrax.api.models.GetAllCoursesResponseItem
import com.example.newedutrax.databinding.ItemLessonBinding
import com.example.newedutrax.databinding.ItemPopulareCoursesBinding
import com.example.newedutrax.ui.fragment.home.HomeAdapter

class LessonAdapter (private val onClick: (item: CourseLecResponseItem) -> Unit):
Adapter<LessonAdapter.ViewHolder>(){
    private val data = ArrayList<CourseLecResponseItem>()

    class ViewHolder(val binding: ItemLessonBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  =
        ViewHolder(
            ItemLessonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.nameLesson.text=item.name
        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }
    fun changeData(newList: List<CourseLecResponseItem>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()

    }
}