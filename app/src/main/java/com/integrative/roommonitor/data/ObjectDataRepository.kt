package com.integrative.roommonitor.data

import com.integrative.roommonitor.api.ObjectDataApi
import javax.inject.Inject

class ObjectDataRepository @Inject constructor(private val objectDataApi: ObjectDataApi) {
    suspend fun getAllObjectsData(id: String) = objectDataApi.getObjects(id)
}