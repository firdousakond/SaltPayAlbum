package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo

data class ImPriceEntity(
    @ColumnInfo(name = "priceLabel")
    val label: String?
)