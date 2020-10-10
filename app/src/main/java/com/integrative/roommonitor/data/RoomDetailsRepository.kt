package com.integrative.roommonitor.data

import com.integrative.roommonitor.api.RoomDetailsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDetailsRepository @Inject constructor(private val roomDetailsApi: RoomDetailsApi)