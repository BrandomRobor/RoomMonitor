package com.integrative.roommonitor.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.integrative.roommonitor.api.RoomDetailsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDetailsRepository @Inject constructor(private val roomDetailsApi: RoomDetailsApi) {
    fun getAllRoomsDetails(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RoomDetailsPagingSource(roomDetailsApi, query) }
        ).liveData
}