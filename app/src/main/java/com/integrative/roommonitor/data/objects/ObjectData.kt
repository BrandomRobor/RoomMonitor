package com.integrative.roommonitor.data.objects

import com.squareup.moshi.Json

data class ObjectData(
    @field:Json(name = "_id") val id: String,
    val status: Boolean,
    val name: String = "",
    val description: String? = null,
    val iconId: String? = null
)