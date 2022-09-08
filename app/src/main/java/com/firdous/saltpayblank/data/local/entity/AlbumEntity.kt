package com.firdous.saltpayblank.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "albumEntry")
data class AlbumEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    var id: Int,
    @Embedded
    @SerializedName("im:artist")
    val artist: ImArtistEntity?,
    @SerializedName("im:image")
    val image: List<ImImageEntity>?,
    @Embedded
    @SerializedName("im:itemCount")
    val itemCount: ImItemCountEntity?,
    @Embedded
    @SerializedName("im:name")
    val name: ImNameEntity?,
    @Embedded
    @SerializedName("im:price")
    val price: ImPriceEntity?,
    @Embedded
    @SerializedName("im:releaseDate")
    val releaseDate: ImReleaseDateEntity?,
    @Embedded
    val title: TitleEntity?,
    var favourite: Boolean?
)