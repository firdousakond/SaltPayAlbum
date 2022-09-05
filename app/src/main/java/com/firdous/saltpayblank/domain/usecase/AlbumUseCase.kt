package com.firdous.saltpayblank.domain.usecase

import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.model.AlbumsResponse
import com.firdous.saltpayblank.domain.repository.IAlbumRepo
import kotlinx.coroutines.flow.Flow

class AlbumUseCase(private val albumRepo: IAlbumRepo) {
    suspend fun getTopAlbums(): Flow<Resource<AlbumsResponse>> = albumRepo.getTopAlbum()

}