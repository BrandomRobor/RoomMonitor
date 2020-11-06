package com.integrative.roommonitor.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.integrative.roommonitor.R
import com.integrative.roommonitor.databinding.FragmentDetailsBinding
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()

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
                detailsRoomTitle.text = roomDetails.title
                detailsRoomDescription.text = roomDetails.description
            }

            detailsObjectRecyclerView.adapter = adapter
            detailsObjectRecyclerView.isNestedScrollingEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}