package com.firdous.saltpayblank.data.remote.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class ImReleaseDate(
    val label: String?,
    @Embedded
    val attributes: Attributes?
)