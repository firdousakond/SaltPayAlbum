package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class ImReleaseDateEntity(
    @ColumnInfo(name = "releaseLabel")
    val label: String?,
    @Embedded
    val attributes: AttributesEntity?
)