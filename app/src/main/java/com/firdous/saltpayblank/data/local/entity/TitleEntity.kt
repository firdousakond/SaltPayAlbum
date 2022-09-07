package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo

data class TitleEntity(
    @ColumnInfo(name = "titleLabel")
    val label: String?
)