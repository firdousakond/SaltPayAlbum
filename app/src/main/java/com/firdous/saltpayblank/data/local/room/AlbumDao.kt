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
    @Query("UPDATE albumEntry set favourite = :isFavourite where id=:id")
    suspend fun setFavourite(id: Int, isFavourite: Boolean) : Int
    @Query("select * from albumEntry where nameLabel LIKE '%' || :searchQuery || '%'")
    fun getSearchedAlbum(searchQuery: String): Flow<List<AlbumEntity>>

}