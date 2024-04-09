package com.example.memerest.presentation.main.ui.feed


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.memerest.R
import com.example.memerest.databinding.MemeItemBinding

class FeedAdapter(private val listener: (ImageUiModel) -> Unit) :
    PagingDataAdapter<ImageUiModel, FeedAdapter.MemeViewHolder>(DIFF_UTIL) {
    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ImageUiModel>() {
            override fun areItemsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel): Boolean =
                oldItem.url == newItem.url

        }
    }

    inner class MemeViewHolder(private val binding: MemeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageUiModel) {
            Glide.with(binding.productImage)
                .load(item.url)
                .diskCacheStrategy(DiskCacheStrategy.DATA).dontTransform()
                .into(binding.productImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder =
        MemeViewHolder(
            MemeItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val repoItem = getItem(position)
        holder.bind(repoItem!!)

    }

}