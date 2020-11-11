package com.integrative.roommonitor.api

import com.integrative.roommonitor.data.ObjectData
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface ObjectDataStatusUpdateApi {
    @Receive
    fun updateStatus(): Flow<ObjectData>

    @Send
    fun requestJoin(id: String)
}