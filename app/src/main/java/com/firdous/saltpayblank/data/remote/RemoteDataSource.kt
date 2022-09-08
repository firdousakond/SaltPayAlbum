package com.firdous.saltpayblank.data.remote

import com.firdous.saltpayblank.data.remote.network.ApiResponse
import com.firdous.saltpayblank.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource (private val apiService: ApiService){

     suspend fun getTopAlbums() = flow {
             try {
                 val albums = apiService.getTopAlbums()
                 emit(ApiResponse.Success(albums))
             } catch (e: Exception) {
                 Timber.e(e)
                 emit(ApiResponse.Error(e.toString()))
             }
        }.flowOn(Dispatchers.IO)

}