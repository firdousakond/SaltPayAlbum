package com.firdous.saltpayblank.domain.repository

import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

interface IAlbumRepo {
    suspend fun getTopAlbum() : Flow<Resource<List<AlbumEntity>>>
    suspend fun insertAlbums(albumEntity: List<AlbumEntity>): Array<Long>
    suspend fun getAlbumFromDb() : Flow<List<AlbumEntity>>
}