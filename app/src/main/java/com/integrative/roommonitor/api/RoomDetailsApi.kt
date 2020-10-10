package com.integrative.roommonitor.api

import com.integrative.roommonitor.data.RoomDetails
import retrofit2.http.GET

interface RoomDetailsApi {
    @GET("rooms")
    suspend fun getAll(): List<RoomDetails>
}