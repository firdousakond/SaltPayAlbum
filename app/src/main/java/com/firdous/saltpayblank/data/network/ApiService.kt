package com.firdous.saltpayblank.data.network

import com.firdous.saltpayblank.data.model.AlbumsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): AlbumsResponse
}