package com.integrative.roommonitor.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.integrative.roommonitor.R
import com.integrative.roommonitor.databinding.FragmentDetailsBinding
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

        binding.apply {
            fragmentDetailsInclude.apply {
                detailsRoomTitle.text = roomDetails.title
                detailsRoomDescription.text = roomDetails.description
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}