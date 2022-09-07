package com.firdous.saltpayblank.data.local.room

import androidx.room.*
import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albumEntry")
    fun getAllAlbums(): Flow<List<AlbumEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlbums(albums: List<AlbumEntity>): Array<Long>
}