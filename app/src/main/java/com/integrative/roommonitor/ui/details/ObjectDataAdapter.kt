package com.integrative.roommonitor.ui.details

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.integrative.roommonitor.data.objects.ObjectData
import com.integrative.roommonitor.databinding.ItemDetailsObjectCardBinding
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial

class ObjectDataAdapter : ListAdapter<ObjectData, ObjectDataAdapter.ObjectDataViewHolder>(
    diffCallback
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ObjectData>() {
            override fun areItemsTheSame(oldItem: ObjectData, newItem: ObjectData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ObjectData, newItem: ObjectData): Boolean =
                oldItem == newItem
        }
    }

    class ObjectDataViewHolder(private val binding: ItemDetailsObjectCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(objectData: ObjectData) {
            binding.apply {
                if (objectData.iconId.isNullOrBlank()) {
                    detailsObjectIcon.isVisible = false
                } else {
                    detailsObjectIcon.isVisible = true
                    detailsObjectIcon.setImageDrawable(
                        IconicsDrawable(
                            root.context,
                            CommunityMaterial.getIcon(objectData.iconId)
                        )
                    )
                }
                root.setCardBackgroundColor(if (objectData.status) Color.GREEN else Color.RED)
                detailsObjectName.text = objectData.name
                detailsObjectDescription.text = objectData.description ?: "No description available"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectDataViewHolder =
        ObjectDataViewHolder(
            ItemDetailsObjectCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ObjectDataViewHolder, position: Int) =
        holder.bind(getItem(position))
}