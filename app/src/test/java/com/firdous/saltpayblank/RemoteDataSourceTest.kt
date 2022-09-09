package com.firdous.saltpayblank

import com.firdous.saltpayblank.data.remote.RemoteDataSource
import com.firdous.saltpayblank.data.remote.model.*
import com.firdous.saltpayblank.data.remote.network.ApiResponse
import com.firdous.saltpayblank.data.remote.network.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    @Mock
    lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun `fetch album list - success response`() = runTest {

        val albumsResponse = AlbumsResponse(
            feed = Feed(
                entry = listOf(
                    Entry(
                        artist = ImArtist(label = "Dua Lipa"),
                        image = listOf(ImImage(label = "s")),
                        itemCount = ImItemCount(label = ""),
                        name = ImName(label = "Song1"),
                        price = ImPrice(label = ""),
                        releaseDate = ImReleaseDate(
                            label = null,
                            attributes = Attributes(label = "10/102022")
                        ),
                        title = Title(label = "Song1"),
                        id = Id(attributes = IdAttributes("100"))
                    ),
                    Entry(
                        artist = ImArtist(label = "Katty Parry"),
                        image = listOf(ImImage(label = "s")),
                        itemCount = ImItemCount(label = ""),
                        name = ImName(label = "Song2"),
                        price = ImPrice(label = ""),
                        releaseDate = ImReleaseDate(
                            label = null,
                            attributes = Attributes(label = "11/102022")
                        ),
                        title = Title(label = "Song2"),
                        id = Id(
                            attributes = IdAttributes("200")
                        )
                    )
                )
            )
        )

        `when`(apiService.getTopAlbums()).thenReturn(albumsResponse)

        val job = launch {
            remoteDataSource.getTopAlbums()
                .collectLatest {
                    delay(3000)
                    assertTrue(it is ApiResponse.Success)
                    assertEquals(it.data.feed?.entry?.get(1)?.id?.attributes?.id, "200")
                }

        }
        job.join()
        job.cancel()
    }
}