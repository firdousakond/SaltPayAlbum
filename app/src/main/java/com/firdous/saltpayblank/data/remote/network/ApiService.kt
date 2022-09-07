package com.firdous.saltpayblank.data.remote.network

import com.firdous.saltpayblank.data.remote.model.AlbumsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): AlbumsResponse
}