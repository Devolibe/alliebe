package com.alliebe.mastersejin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alliebe.mastersejin.R
import com.alliebe.mastersejin.databinding.ParentItemBinding
import com.alliebe.mastersejin.dtos.StoryParentDTO
import com.alliebe.mastersejin.adapter.StoryParentAdapter.ViewHolder

class StoryParentAdapter(private val data: List<StoryParentDTO>) : RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(private val itemView: View, val binding: ParentItemBinding) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.parent_item, parent, false) as ParentItemBinding
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = data[position]
        holder.binding.rvChildren.adapter = StoryChildCommentAdapter(data[position].children_comment)
        holder.binding.rvAlbum.adapter = StoryChildAlbumAdapter(data[position].children_album)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = data.size
}