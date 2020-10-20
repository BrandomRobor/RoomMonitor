package com.integrative.roommonitor.data

import com.squareup.moshi.Json

data class RoomDetails(
    @Json(name = "_id")
    val id: String,
    val title: String,
    val description: String?,
    val location: String?,
    val iconId: String?
)