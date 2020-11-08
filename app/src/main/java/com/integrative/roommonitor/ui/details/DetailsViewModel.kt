package com.integrative.roommonitor.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.integrative.roommonitor.data.ObjectDataRepository
import com.integrative.roommonitor.data.RoomObjects
import kotlinx.coroutines.Dispatchers

class DetailsViewModel @ViewModelInject constructor(private val objectDataRepository: ObjectDataRepository) :
    ViewModel() {
    fun getAllObjectsData(id: String): LiveData<RoomObjects> = liveData(Dispatchers.IO) {
        emit(objectDataRepository.getAllObjectsData(id))
    }
}