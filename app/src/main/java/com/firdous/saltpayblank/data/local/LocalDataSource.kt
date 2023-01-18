package com.firdous.saltpayblank.data.local

import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import com.firdous.saltpayblank.data.local.room.AlbumDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val albumDao: AlbumDao) {
    fun getAllAlbums(): Flow<List<AlbumEntity>> = albumDao.getAllAlbums()
    fun getSearchAlbums(query: String): Flow<List<AlbumEntity>> = albumDao.getSearchedAlbum(query)
    suspend fun insertAlbums(albums: List<AlbumEntity>): Array<Long> = albumDao.insertAlbums(albums)
    suspend fun setFavourite(id: Int, isFavourite: Boolean) = albumDao.setFavourite(id, isFavourite)
}