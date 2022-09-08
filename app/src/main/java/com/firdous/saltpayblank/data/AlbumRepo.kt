package com.firdous.saltpayblank.data

import android.content.Context
import com.firdous.saltpayblank.data.local.LocalDataSource
import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import com.firdous.saltpayblank.data.remote.RemoteDataSource
import com.firdous.saltpayblank.data.remote.model.AlbumsResponse
import com.firdous.saltpayblank.data.remote.network.ApiResponse
import com.firdous.saltpayblank.domain.repository.IAlbumRepo
import com.firdous.saltpayblank.util.NetworkUtil
import com.firdous.saltpayblank.util.toAlbumEntity
import kotlinx.coroutines.flow.Flow

class AlbumRepo(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource,  private val context : Context): IAlbumRepo {

    override suspend fun getTopAlbum(): Flow<Resource<List<AlbumEntity>>>  =
        object : NetworkBoundResource<List<AlbumEntity>, AlbumsResponse>() {

            override fun shouldFetch(): Boolean {
                return NetworkUtil.isInternetConnected(context)
            }

            override fun loadFromDB(): Flow<List<AlbumEntity>> {
                return localDataSource.getAllAlbums()
            }

            override suspend fun createCall(): Flow<ApiResponse<AlbumsResponse>> {
                return remoteDataSource.getTopAlbums()
            }

            override suspend fun saveCallResult(data: AlbumsResponse) {
                val entity = data.feed?.entry?.toAlbumEntity()
                if (entity != null) {
                    localDataSource.insertAlbums(entity)
                }
            }

        }.asFlow()

    override suspend fun setFavourite(id: Int, isFavourite: Boolean) {
        localDataSource.setFavourite(id, isFavourite)
    }

}