package com.integrative.roommonitor.ui.rooms

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.integrative.roommonitor.data.RoomDetailsRepository

class RoomsViewModel @ViewModelInject constructor(private val roomDetailsRepository: RoomDetailsRepository) :
    ViewModel() {
    val searchQuery = MutableLiveData("")
    val roomsDetails = searchQuery.switchMap {
        roomDetailsRepository.getAllRoomsDetails(it).cachedIn(viewModelScope)
    }
}