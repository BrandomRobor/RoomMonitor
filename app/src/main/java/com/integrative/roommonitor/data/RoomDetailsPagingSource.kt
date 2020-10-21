package com.integrative.roommonitor.data

import androidx.paging.PagingSource
import com.integrative.roommonitor.api.RoomDetailsApi
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale

class RoomDetailsPagingSource(
    private val roomDetailsApi: RoomDetailsApi,
    private val query: String
) :
    PagingSource<Int, RoomDetails>() {
    companion object {
        private const val STARTING_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RoomDetails> {
        val position = params.key ?: STARTING_PAGE
        return try {
            val roomsDetails = roomDetailsApi.getRooms(position, params.loadSize)
            val trimmedQuery = query.toLowerCase(Locale.ROOT).trim()
            val filteredDetails =
                if (trimmedQuery.isEmpty()) roomsDetails else roomsDetails.filter {
                    it.title.toLowerCase(Locale.ROOT).contains(trimmedQuery)
                }
            LoadResult.Page(
                filteredDetails,
                if (position == STARTING_PAGE) null else position - 1,
                if (filteredDetails.isEmpty()) null else position + 1
            )
        } catch (io: IOException) {
            LoadResult.Error(io)
        } catch (http: HttpException) {
            LoadResult.Error(http)
        }
    }
}