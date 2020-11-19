package com.integrative.roommonitor.data

import androidx.lifecycle.MutableLiveData
import com.integrative.roommonitor.api.ObjectDataApi
import io.socket.client.Socket
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObjectDataRepository @Inject constructor(
    private val objectDataApi: ObjectDataApi,
    private val socket: Socket
) {
    suspend fun getAllObjectsData(id: String): RoomObjects = objectDataApi.getObjects(id)

    fun requestUpdates(id: String) {
        socket.on(Socket.EVENT_CONNECT) {
            socket.emit("joinUpdates", id)
        }.on("objectUpdate") {
            val objectInfo = it[0] as JSONObject
            val transformedInfo = ObjectData(
                objectInfo.getString("id"),
                "",
                null,
                null,
                objectInfo.getBoolean("status")
            )
            liveObjectInfo.postValue(transformedInfo)
        }
        socket.connect()
    }

    val liveObjectInfo = MutableLiveData<ObjectData>()

    fun closeConnection() {
        socket.close()
    }
}