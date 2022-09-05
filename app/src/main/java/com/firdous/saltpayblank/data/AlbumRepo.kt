package com.firdous.saltpayblank.data

import com.firdous.saltpayblank.data.model.AlbumsResponse
import com.firdous.saltpayblank.domain.repository.IAlbumRepo
import kotlinx.coroutines.flow.Flow

class AlbumRepo(private val remoteDataSource: RemoteDataSource): IAlbumRepo {
    override suspend fun getTopAlbum(): Flow<Resource<AlbumsResponse>> = remoteDataSource.getTopAlbums()
}