package com.integrative.roommonitor

data class RoomDetails(
    val id: String,
    val title: String,
    val description: String?,
    val location: String?,
    val iconId: String?
)