package com.firdous.saltpayblank.domain.usecase

import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import com.firdous.saltpayblank.domain.repository.IAlbumRepo
import kotlinx.coroutines.flow.Flow

class AlbumListUseCase(private val albumRepo: IAlbumRepo) {
    suspend fun getTopAlbums(): Flow<Resource<List<AlbumEntity>>> = albumRepo.getTopAlbum()
}