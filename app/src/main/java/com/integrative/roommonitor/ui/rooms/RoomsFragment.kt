package com.integrative.roommonitor.ui.rooms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment() {
    private val viewModel by viewModels<RoomsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.roomsDetails.observe(viewLifecycleOwner) {

        }
    }
}