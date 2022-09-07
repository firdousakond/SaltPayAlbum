package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo

data class AttributesEntity (
    @ColumnInfo(name = "attributeLabel")
    val label: String?
        )