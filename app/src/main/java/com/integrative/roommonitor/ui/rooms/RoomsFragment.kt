package com.integrative.roommonitor.ui.rooms

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.paging.LoadState
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
            roomsRecylerView.adapter = adapter.withLoadStateHeaderAndFooter(
                RoomDetailsLoadStateAdapter { adapter.retry() },
                RoomDetailsLoadStateAdapter { adapter.retry() }
            )
            roomsButtonRetry.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.roomsDetails.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener {
            binding.apply {
                roomsProgressBar.isVisible = it.source.refresh is LoadState.Loading
                roomsRecylerView.isVisible = it.source.refresh is LoadState.NotLoading
                roomsButtonRetry.isVisible = it.source.refresh is LoadState.Error
                roomsTextError.isVisible = it.source.refresh is LoadState.Error

                if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && adapter.itemCount < 1) {
                    roomsRecylerView.isVisible = false
                    roomsZeroRooms.isVisible = true
                } else {
                    roomsZeroRooms.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}