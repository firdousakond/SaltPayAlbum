package com.firdous.saltpayblank

import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.local.entity.*
import com.firdous.saltpayblank.domain.usecase.AlbumListUseCase
import com.firdous.saltpayblank.domain.usecase.FavouriteAlbumUseCase
import com.firdous.saltpayblank.ui.AlbumViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
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
class AlbumViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var albumUseCase: AlbumListUseCase

    @Mock
    lateinit var favouriteAlbumUseCase: FavouriteAlbumUseCase

    private lateinit var viewModel: AlbumViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = AlbumViewModel(albumUseCase, favouriteAlbumUseCase)
    }

    @Test
    fun `fetch album list - success response`() = runTest(UnconfinedTestDispatcher()) {
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

        `when`(
            albumUseCase.getTopAlbums()
        ).thenReturn(
            flowOf(Resource.Success(data = response))
        )
        viewModel.getTopAlbums()
        val job = launch {
            viewModel.albumStateFlow.collectLatest {
                delay(3000)
                assertTrue(it is Resource.Success)
                assertEquals(it.data[1].id, 200)
            }
        }
        job.cancel()
    }

    @Test
    fun `fetch album list - Error response`() = runTest(UnconfinedTestDispatcher()) {

        val errorMessage = "error occurred"

        `when`(
            albumUseCase.getTopAlbums()
        ).thenReturn(
            flowOf(Resource.Error(message = errorMessage))
        )
        viewModel.getTopAlbums()
        val job = launch {
            viewModel.albumStateFlow.collectLatest {
                delay(3000)
                assertTrue(it is Resource.Error)
                assertEquals(it.message, errorMessage)
            }
        }
        job.cancel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}
