package com.alliebe.mastersejin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alliebe.mastersejin.R
import com.alliebe.mastersejin.databinding.ChildItemBinding
import com.alliebe.mastersejin.dtos.StoryChildCommentDTO
import com.alliebe.mastersejin.adapter.StoryChildCommentAdapter.ViewHolder

class StoryChildCommentAdapter(private val childData: List<StoryChildCommentDTO>?) : RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(private val itemView : View, val binding: ChildItemBinding) :
        RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.child_item, parent, false) as ChildItemBinding
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if ((childData?.size ?: 0) > position)
            holder.binding.data = childData?.get(position)

        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = childData?.size ?: 0
}