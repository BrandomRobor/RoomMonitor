package com.integrative.roommonitor.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.integrative.roommonitor.data.ObjectData
import com.integrative.roommonitor.databinding.ItemDetailsObjectCardBinding
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial

class ObjectDataAdapter : RecyclerView.Adapter<ObjectDataAdapter.ObjectDataViewHolder>() {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ObjectData>() {
            override fun areItemsTheSame(oldItem: ObjectData, newItem: ObjectData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ObjectData, newItem: ObjectData): Boolean =
                oldItem == newItem
        }
    }

    private val asyncDiffer = AsyncListDiffer(this, diffCallback)

    fun submitData(newList: List<ObjectData>) {
        asyncDiffer.submitList(newList)
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
                detailsObjectName.text = objectData.name
                detailsObjectDescription.text = objectData.description ?: "No description available"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectDataViewHolder {
        val binding =
            ItemDetailsObjectCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ObjectDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ObjectDataViewHolder, position: Int) {
        val objectData = asyncDiffer.currentList[position]
        holder.bind(objectData)
    }

    override fun getItemCount(): Int =
        asyncDiffer.currentList.size
}