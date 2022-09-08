package com.firdous.saltpayblank.domain.repository

import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

interface IAlbumRepo {
    suspend fun getTopAlbum() : Flow<Resource<List<AlbumEntity>>>
    suspend fun setFavourite(id: Int, isFavourite: Boolean)
}