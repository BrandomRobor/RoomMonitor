package com.integrative.roommonitor.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.integrative.roommonitor.data.objects.ObjectDataRepository
import com.integrative.roommonitor.data.objects.RoomObjects

class DetailsViewModel @ViewModelInject constructor(private val objectDataRepository: ObjectDataRepository) :
    ViewModel() {
    suspend fun getAllObjectsData(id: String): RoomObjects =
        objectDataRepository.getAllObjectsData(id)

    fun requestUpdates(id: String) {
        objectDataRepository.requestUpdates(id)
    }

    fun closeConnection() {
        objectDataRepository.closeConnection()
    }

    val liveObjectInfo = objectDataRepository.liveObjectInfo
}