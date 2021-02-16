package com.alliebe.mastersejin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alliebe.mastersejin.R
import com.alliebe.mastersejin.databinding.ChildAlbumItemBinding
import com.alliebe.mastersejin.dtos.StoryChildAlbumDTO
import com.alliebe.mastersejin.adapter.StoryChildAlbumAdapter.ViewHolder

class StoryChildAlbumAdapter(private val childAlbumData: List<StoryChildAlbumDTO>?) : RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(private val itemView : View, val binding: ChildAlbumItemBinding) :
        RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.child_album_item, parent, false) as ChildAlbumItemBinding
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if((childAlbumData?.size ?: 0) > position)
            holder.binding.data = childAlbumData?.get(position)

        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = childAlbumData?.size ?: 0
}