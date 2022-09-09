package com.firdous.saltpayblank

import android.content.Context
import com.firdous.saltpayblank.data.AlbumRepo
import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.local.LocalDataSource
import com.firdous.saltpayblank.data.local.entity.*
import com.firdous.saltpayblank.data.remote.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumRepoTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    private lateinit var albumRepo: AlbumRepo

    @Before
    fun setup() {
        val context = mock(Context::class.java)
        albumRepo = AlbumRepo(localDataSource, remoteDataSource, context)
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

        `when`(localDataSource.getAllAlbums()).thenReturn(
            flowOf(response)
        )

        val job = launch {
            albumRepo.getTopAlbum()
                .collectLatest {
                    delay(3000)
                    assertTrue(it is Resource.Success)
                    assertEquals(it.data[1].id, 200)
                }

        }
        job.join()
        job.cancel()
    }


}