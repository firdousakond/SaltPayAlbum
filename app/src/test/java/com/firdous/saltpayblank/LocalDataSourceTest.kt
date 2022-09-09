package com.firdous.saltpayblank

import com.firdous.saltpayblank.data.local.LocalDataSource
import com.firdous.saltpayblank.data.local.entity.*
import com.firdous.saltpayblank.data.local.room.AlbumDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocalDataSourceTest {

    private lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var albumDao: AlbumDao

    @Before
    fun setup() {
        localDataSource = LocalDataSource(albumDao)
    }

    @Test
    fun `fetch album list - success response`() = runTest {
        val response = listOf(
            AlbumEntity(
                id = 100,
                artist = ImArtistEntity(label = "Dua Lipa"),
                image = listOf(ImImageEntity(label = "s")),
                itemCount = ImItemCountEntity(label = ""),
                name = ImNameEntity(label = "Song1"),
                price = ImPriceEntity(label = ""),
                releaseDate = ImReleaseDateEntity(
                    label = null,
                    attributes = AttributesEntity(label = "10/102022")
                ),
                title = TitleEntity(label = "Song1"),
                favourite = false
            ),
            AlbumEntity(
                id = 200,
                artist = ImArtistEntity(label = "Katty Parry"),
                image = listOf(ImImageEntity(label = "s")),
                itemCount = ImItemCountEntity(label = ""),
                name = ImNameEntity(label = "Song2"),
                price = ImPriceEntity(label = ""),
                releaseDate = ImReleaseDateEntity(
                    label = null,
                    attributes = AttributesEntity(label = "11/102022")
                ),
                title = TitleEntity(label = "Song2"),
                favourite = false
            )
        )

        Mockito.`when`(albumDao.getAllAlbums()).thenReturn(
            flowOf(response)
        )

        val job = launch {
            localDataSource.getAllAlbums()
                .collectLatest {
                    assertEquals(it[0].id, 100)
                }

        }
        job.join()
        job.cancel()

        }

    @Test
    fun `insert album list`() = runTest {
        val album = listOf(
            AlbumEntity(
                id = 100,
                artist = ImArtistEntity(label = "Dua Lipa"),
                image = listOf(ImImageEntity(label = "s")),
                itemCount = ImItemCountEntity(label = ""),
                name = ImNameEntity(label = "Song1"),
                price = ImPriceEntity(label = ""),
                releaseDate = ImReleaseDateEntity(
                    label = null,
                    attributes = AttributesEntity(label = "10/102022")
                ),
                title = TitleEntity(label = "Song1"),
                favourite = false
            ),
            AlbumEntity(
                id = 200,
                artist = ImArtistEntity(label = "Katty Parry"),
                image = listOf(ImImageEntity(label = "s")),
                itemCount = ImItemCountEntity(label = ""),
                name = ImNameEntity(label = "Song2"),
                price = ImPriceEntity(label = ""),
                releaseDate = ImReleaseDateEntity(
                    label = null,
                    attributes = AttributesEntity(label = "11/102022")
                ),
                title = TitleEntity(label = "Song2"),
                favourite = false
            )
        )

        Mockito.`when`(albumDao.insertAlbums(album)).thenReturn(
            arrayOf(1,2,3)
        )

        val job = launch {
            val result = localDataSource.insertAlbums(album)
            assertEquals(result.size ,3)
            assertEquals(result[1],2)

        }
        job.join()
        job.cancel()

    }

    @Test
    fun `set favourite album list`() = runTest {
        Mockito.`when`(albumDao.setFavourite(1,true)).thenReturn(
           100
        )

        val job = launch {
            val result = localDataSource.setFavourite(1, true)
            assertEquals(result ,100)

        }
        job.join()
        job.cancel()

    }


}