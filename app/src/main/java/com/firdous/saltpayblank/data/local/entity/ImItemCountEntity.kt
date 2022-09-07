package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo

data class ImItemCountEntity(
    @ColumnInfo(name = "itemCountLabel")
    val label: String?
)