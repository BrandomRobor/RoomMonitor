package com.integrative.roommonitor.ui.rooms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.integrative.roommonitor.R
import com.integrative.roommonitor.databinding.FragmentRoomsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment(R.layout.fragment_rooms) {
    private val viewModel by viewModels<RoomsViewModel>()
    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRoomsBinding.bind(view)
        val adapter = RoomDetailsAdapter()

        binding.apply {
            roomsRecylerView.setHasFixedSize(true)
            roomsRecylerView.adapter = adapter
        }

        viewModel.roomsDetails.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}