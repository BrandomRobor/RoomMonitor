package com.integrative.roommonitor.api

import com.integrative.roommonitor.data.objects.RoomObjects
import retrofit2.http.GET
import retrofit2.http.Path

interface ObjectDataApi {
    @GET("objects/{id}")
    suspend fun getObjects(
        @Path("id") id: String
    ): RoomObjects
}