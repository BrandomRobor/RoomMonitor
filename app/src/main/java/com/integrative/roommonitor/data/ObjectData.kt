package com.integrative.roommonitor.data

import com.squareup.moshi.Json

data class ObjectData(
    @field:Json(name = "_id") val id: String,
    val name: String,
    val description: String?,
    val iconId: String?,
    var status: Boolean
)