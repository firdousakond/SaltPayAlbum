package com.firdous.saltpayblank.data

import com.firdous.saltpayblank.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource (private val apiService: ApiService){

     suspend fun getTopAlbums() = flow {
             try {
                 val albums = apiService.getTopAlbums()
                 emit(Resource.Success(albums))
             } catch (e: Exception) {
                 Timber.e(e)
                 emit(Resource.Error(e.toString()))
             }
        }.flowOn(Dispatchers.IO)

}