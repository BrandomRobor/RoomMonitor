package com.integrative.roommonitor.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RoomDetails(
    @field:Json(name = "_id") val id: String,
    val title: String,
    val description: String?,
    val location: String?,
    val iconId: String?
) : Parcelable