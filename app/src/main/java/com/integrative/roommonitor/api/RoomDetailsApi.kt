package com.integrative.roommonitor.api

import com.integrative.roommonitor.data.rooms.RoomDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface RoomDetailsApi {
    @GET("rooms")
    suspend fun getRooms(
        @Query("page") page: Int,
        @Query("limit") perPage: Int
    ): List<RoomDetails>
}