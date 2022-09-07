package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo

data class ImNameEntity(
    @ColumnInfo(name = "nameLabel")
    val label: String?
)