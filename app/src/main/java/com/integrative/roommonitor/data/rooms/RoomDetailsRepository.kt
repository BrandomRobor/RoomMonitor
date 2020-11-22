package com.integrative.roommonitor.data.rooms

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.integrative.roommonitor.api.RoomDetailsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDetailsRepository @Inject constructor(private val roomDetailsApi: RoomDetailsApi) {
    fun getAllRoomsDetails() =
        Pager(
            PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            )
        ) {
            RoomDetailsPagingSource(roomDetailsApi)
        }.liveData
}