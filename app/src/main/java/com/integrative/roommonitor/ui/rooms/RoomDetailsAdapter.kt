package com.integrative.roommonitor.ui.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.integrative.roommonitor.R
import com.integrative.roommonitor.data.RoomDetails
import com.integrative.roommonitor.databinding.ItemRoomDetailsBinding

class RoomDetailsAdapter(private val listener: OnDetailsCardClickListener) :
    PagingDataAdapter<RoomDetails, RoomDetailsAdapter.DetailsViewHolder>(
        detailsComparator
    ) {
    inner class DetailsViewHolder(private val binding: ItemRoomDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let {
                        listener.onCardClick(item)
                    }
                }
            }
        }

        fun bind(roomDetails: RoomDetails) {
            binding.apply {
                detailsRoomIcon.setImageResource(R.drawable.ic_info)
                detailsRoomTitle.text = roomDetails.title
                detailsRoomDescription.text = roomDetails.description ?: "No description available"
            }
        }
    }

    interface OnDetailsCardClickListener {
        fun onCardClick(roomDetails: RoomDetails)
    }

    companion object {
        private val detailsComparator = object : DiffUtil.ItemCallback<RoomDetails>() {
            override fun areItemsTheSame(oldItem: RoomDetails, newItem: RoomDetails): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RoomDetails, newItem: RoomDetails): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding =
            ItemRoomDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val currentItem = getItem(position)

        currentItem?.let {
            holder.bind(it)
        }
    }
}
















