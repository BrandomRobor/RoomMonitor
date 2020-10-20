package com.integrative.roommonitor.ui.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.integrative.roommonitor.databinding.ItemPagingRetryBinding

class RoomDetailsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<RoomDetailsLoadStateAdapter.LoadStateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            ItemPagingRetryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: ItemPagingRetryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.pagingRetryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                pagingProgressBar.isVisible = loadState is LoadState.Loading
                pagingRetryButton.isVisible = loadState !is LoadState.Loading
                pagingErrorMessage.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}














