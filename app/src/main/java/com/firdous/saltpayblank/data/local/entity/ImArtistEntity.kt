package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo

data class ImArtistEntity(
    @ColumnInfo(name = "artistLabel")
    val label: String?
)