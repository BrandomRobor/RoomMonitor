package com.integrative.roommonitor.data

import android.util.Log
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
            Log.d("SocketMessage", "Connected!")
        }.on(Socket.EVENT_CONNECTING) {
            Log.d("SocketMessage", "Connecting...")
        }.on(Socket.EVENT_CONNECT_ERROR) {
            Log.d("SocketMessage", "Error connecting!")
        }.on("objectUpdate") {
            val objectInfo = it[0] as JSONObject
            val transformedInfo = ObjectInfo(
                objectInfo.getString("id"),
                objectInfo.getBoolean("status")
            )
            liveObjectInfo.postValue(transformedInfo)
        }
        socket.connect()
    }

    val liveObjectInfo = MutableLiveData<ObjectInfo>()

    fun closeConnection() {
        socket.close()
    }
}