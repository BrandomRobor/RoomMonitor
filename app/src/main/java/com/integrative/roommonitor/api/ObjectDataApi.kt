package com.integrative.roommonitor.api

import com.integrative.roommonitor.data.ObjectData
import retrofit2.http.GET
import retrofit2.http.Query

interface ObjectDataApi {
    @GET("objects")
    suspend fun getObjects(
        @Query("id") id: String
    ): List<ObjectData>
}