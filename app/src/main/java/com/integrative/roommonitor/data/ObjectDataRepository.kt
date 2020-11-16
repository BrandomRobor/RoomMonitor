package com.integrative.roommonitor.data

import android.util.Log
import com.integrative.roommonitor.api.ObjectDataApi
import io.socket.client.Socket
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
        }
        socket.connect()
    }

    fun closeConnection() {
        socket.close()
    }
}