package com.integrative.roommonitor.ui.rooms

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.integrative.roommonitor.data.rooms.RoomDetailsRepository

class RoomsViewModel @ViewModelInject constructor(roomDetailsRepository: RoomDetailsRepository) :
    ViewModel() {
    val roomsDetails = roomDetailsRepository.getAllRoomsDetails().cachedIn(viewModelScope)
}