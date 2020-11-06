package com.integrative.roommonitor.ui.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.integrative.roommonitor.data.RoomDetails
import com.integrative.roommonitor.databinding.ItemRoomDetailsCardBinding
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial

class RoomDetailsAdapter(private val listener: OnDetailsCardClickListener) :
    PagingDataAdapter<RoomDetails, RoomDetailsAdapter.DetailsViewHolder>(
        detailsComparator
    ) {
    inner class DetailsViewHolder(private val binding: ItemRoomDetailsCardBinding) :
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
            binding.itemRoomInclude.apply {
                roomDetails.iconId?.let {
                    detailsRoomIcon.isVisible = true
                    detailsRoomIcon.setImageDrawable(
                        IconicsDrawable(
                            binding.root.context,
                            CommunityMaterial.getIcon(it)
                        )
                    )
                }
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
            ItemRoomDetailsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val currentItem = getItem(position)

        currentItem?.let {
            holder.bind(it)
        }
    }
}
















