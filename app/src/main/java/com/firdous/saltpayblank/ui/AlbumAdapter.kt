package com.firdous.saltpayblank.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import com.firdous.saltpayblank.databinding.ItemAlbumBinding

class AlbumAdapter(private val context: Context) :
    ListAdapter<AlbumEntity, AlbumAdapter.AlbumViewHolder>(AlbumDiffUtil()) {

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: AlbumEntity) {
            binding.entry = entry
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = ItemAlbumBinding.inflate(LayoutInflater.from(context), parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AlbumDiffUtil : DiffUtil.ItemCallback<AlbumEntity>() {
    override fun areItemsTheSame(
        oldItem: AlbumEntity,
        newItem: AlbumEntity
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AlbumEntity,
        newItem: AlbumEntity
    ): Boolean {
        return oldItem == newItem
    }

}
