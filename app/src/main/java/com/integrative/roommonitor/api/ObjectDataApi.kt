package com.integrative.roommonitor.api

import com.integrative.roommonitor.data.RoomObjects
import retrofit2.http.GET
import retrofit2.http.Query

interface ObjectDataApi {
    @GET("objects")
    suspend fun getObjects(
        @Query("id") id: String
    ): RoomObjects
}