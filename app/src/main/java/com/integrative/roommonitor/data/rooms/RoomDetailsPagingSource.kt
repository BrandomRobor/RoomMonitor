package com.integrative.roommonitor.data.rooms

import androidx.paging.PagingSource
import com.integrative.roommonitor.api.RoomDetailsApi
import retrofit2.HttpException
import java.io.IOException

class RoomDetailsPagingSource(
    private val roomDetailsApi: RoomDetailsApi
) : PagingSource<Int, RoomDetails>() {
    companion object {
        private const val STARTING_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RoomDetails> =
        try {
            val position = params.key ?: STARTING_PAGE
            val roomsDetails = roomDetailsApi.getRooms(position, params.loadSize)
            LoadResult.Page(
                roomsDetails,
                if (position == STARTING_PAGE) null else position - 1,
                if (roomsDetails.isEmpty()) null else position + 1
            )
        } catch (io: IOException) {
            LoadResult.Error(io)
        } catch (http: HttpException) {
            LoadResult.Error(http)
        }
}