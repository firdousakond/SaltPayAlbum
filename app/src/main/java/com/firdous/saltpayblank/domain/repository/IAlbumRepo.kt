package com.firdous.saltpayblank.domain.repository

import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.model.AlbumsResponse
import kotlinx.coroutines.flow.Flow

interface IAlbumRepo {
    suspend fun getTopAlbum() : Flow<Resource<AlbumsResponse>>
}