package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo

data class ImImageEntity(
    @ColumnInfo(name = "imageLabel")
    val label: String?
)