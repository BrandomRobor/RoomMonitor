package com.integrative.roommonitor.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.integrative.roommonitor.R
import com.integrative.roommonitor.data.ObjectData
import com.integrative.roommonitor.databinding.FragmentDetailsBinding
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        val roomDetails = args.roomDetails
        val adapter = ObjectDataAdapter()

        binding.apply {
            fragmentDetailsInclude.apply {
                if (roomDetails.iconId.isNullOrBlank()) {
                    detailsRoomIcon.isVisible = false
                } else {
                    detailsRoomIcon.isVisible = true
                    detailsRoomIcon.setImageDrawable(
                        IconicsDrawable(
                            requireContext(),
                            CommunityMaterial.getIcon(roomDetails.iconId)
                        )
                    )
                }

                if (roomDetails.location.isNullOrBlank()) {
                    detailsRoomLocation.isVisible = false
                } else {
                    detailsRoomLocation.isVisible = true
                    detailsRoomLocation.text =
                        getString(R.string.location_string, roomDetails.location)
                }

                detailsRoomTitle.text = roomDetails.title
                detailsRoomDescription.text = roomDetails.description
            }

            detailsObjectRecyclerView.adapter = adapter

            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitList(viewModel.getAllObjectsData(roomDetails.id).objects)
            }

            viewModel.liveObjectInfo.observe(viewLifecycleOwner) { newInfo ->
                viewLifecycleOwner.lifecycleScope.launch {
                    adapter.submitList(mapList(adapter.currentList, newInfo))
                }
            }
        }

        viewModel.requestUpdates(roomDetails.id)
    }

    private suspend fun mapList(list: List<ObjectData>, update: ObjectData): List<ObjectData> =
        withContext(Dispatchers.Default) {
            list.map {
                it.copy(status = if (it.id == update.id) update.status else it.status)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.detailsObjectRecyclerView.adapter = null
        _binding = null
        viewModel.closeConnection()
    }
}