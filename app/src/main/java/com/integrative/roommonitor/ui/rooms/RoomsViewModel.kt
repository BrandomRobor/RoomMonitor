package com.integrative.roommonitor.ui.rooms

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.integrative.roommonitor.data.RoomDetailsRepository

class RoomsViewModel @ViewModelInject constructor(private val roomDetailsRepository: RoomDetailsRepository) :
    ViewModel()