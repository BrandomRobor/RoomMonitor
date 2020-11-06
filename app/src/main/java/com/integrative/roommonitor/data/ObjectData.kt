package com.integrative.roommonitor.data

import androidx.lifecycle.MutableLiveData

data class ObjectData(
    val id: String,
    val name: String,
    val description: String?,
    val iconId: String?,
    val status: MutableLiveData<Boolean>
)