package com.firdous.saltpayblank.data.remote.model

import androidx.room.Embedded
import androidx.room.Entity

data class Id(
    @Embedded
    val attributes: IdAttributes?
)