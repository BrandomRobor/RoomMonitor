package com.integrative.roommonitor.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.integrative.roommonitor.data.ObjectDataRepository
import kotlinx.coroutines.Dispatchers

class DetailsViewModel @ViewModelInject constructor(private val objectDataRepository: ObjectDataRepository) :
    ViewModel() {
    fun getAllObjectsData(id: String) = liveData(Dispatchers.IO) {
        emit(objectDataRepository.getAllObjectsData(id))
    }
}