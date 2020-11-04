package com.integrative.roommonitor.ui.rooms

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.integrative.roommonitor.data.RoomDetailsRepository

class RoomsViewModel @ViewModelInject constructor(roomDetailsRepository: RoomDetailsRepository) :
    ViewModel() {
    val searchQuery = MutableLiveData("")
    val roomsDetails = roomDetailsRepository.getAllRoomsDetails().cachedIn(viewModelScope)
}