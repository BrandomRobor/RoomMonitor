package com.integrative.roommonitor.ui.rooms

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.filter
import com.integrative.roommonitor.R
import com.integrative.roommonitor.data.RoomDetails
import com.integrative.roommonitor.databinding.FragmentRoomsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment(R.layout.fragment_rooms),
    RoomDetailsAdapter.OnDetailsCardClickListener {
    private val viewModel by viewModels<RoomsViewModel>()
    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RoomDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRoomsBinding.bind(view)
        adapter = RoomDetailsAdapter(this)

        binding.apply {
            roomsRecyclerView.setHasFixedSize(true)
            roomsRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                RoomDetailsLoadStateAdapter { adapter.retry() },
                RoomDetailsLoadStateAdapter { adapter.retry() }
            )
            roomsButtonRetry.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.roomsDetails.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })

        adapter.addLoadStateListener {
            binding.apply {
                roomsSwipeLayout.isRefreshing = it.source.refresh is LoadState.Loading
                roomsRecyclerView.isVisible = it.source.refresh is LoadState.NotLoading
                roomsButtonRetry.isVisible = it.source.refresh is LoadState.Error
                roomsTextError.isVisible = it.source.refresh is LoadState.Error

                if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && adapter.itemCount < 1) {
                    roomsRecyclerView.isVisible = false
                    roomsZeroRooms.isVisible = true
                } else {
                    roomsZeroRooms.isVisible = false
                }
            }
        }

        binding.roomsSwipeLayout.setOnRefreshListener {
            adapter.refresh()
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_rooms, menu)

        val searchItem = menu.findItem(R.id.rooms_search_button)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filteredData = viewModel.roomsDetails.value?.filter {
                    it.title.contains(query!!.trim(), true)
                }
                binding.roomsRecyclerView.scrollToPosition(0)
                adapter.submitData(viewLifecycleOwner.lifecycle, filteredData!!)
                binding.roomsZeroRooms.isVisible = adapter.snapshot().size < 1
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean = true

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                binding.roomsZeroRooms.isVisible = false
                binding.roomsRecyclerView.scrollToPosition(0)
                adapter.submitData(viewLifecycleOwner.lifecycle, viewModel.roomsDetails.value!!)
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.rooms_refresh_button -> {
                binding.roomsSwipeLayout.isRefreshing = true
                adapter.refresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onCardClick(roomDetails: RoomDetails) {
        val action =
            RoomsFragmentDirections.actionNavigationRoomsFragmentToNavigationDetailsFragment(
                roomDetails
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.roomsRecyclerView.adapter = null
        _binding = null
    }
}