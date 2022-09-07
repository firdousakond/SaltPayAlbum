package com.firdous.saltpayblank.data.remote.model

import com.google.gson.annotations.SerializedName

data class Entry(
    @SerializedName("im:artist")
    val artist: ImArtist?,
    @SerializedName("im:image")
    val image: List<ImImage>?,
    @SerializedName("im:itemCount")
    val itemCount: ImItemCount?,
    @SerializedName("im:name")
    val name: ImName?,
    @SerializedName("im:price")
    val price: ImPrice?,
    @SerializedName("im:releaseDate")
    val releaseDate: ImReleaseDate?,
    val title: Title?
)