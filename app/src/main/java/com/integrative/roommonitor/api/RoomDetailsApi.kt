package com.integrative.roommonitor.api

import com.integrative.roommonitor.data.RoomDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface RoomDetailsApi {
    @GET("rooms")
    suspend fun getRooms(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int
    ): List<RoomDetails>
}