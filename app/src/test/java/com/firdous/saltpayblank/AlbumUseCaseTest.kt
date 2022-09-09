package com.firdous.saltpayblank

import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.local.entity.*
import com.firdous.saltpayblank.domain.repository.IAlbumRepo
import com.firdous.saltpayblank.domain.usecase.AlbumListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
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
class AlbumUseCaseTest {

    @Mock
    lateinit var iAlbumRepo: IAlbumRepo
    private lateinit var albumListUseCase: AlbumListUseCase

    @Before
    fun setup(){
        albumListUseCase = AlbumListUseCase(iAlbumRepo)
    }

    @Test
    fun `fetch album list - success response`() = runTest{

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

        `when`(iAlbumRepo.getTopAlbum()).thenReturn(
            flowOf(Resource.Success(data = response)))

        val job = launch {
            albumListUseCase.getTopAlbums()
                .collectLatest {
                   assertTrue(it is Resource.Success)
                   assertEquals(it.data[0].id, 100)
                }

        }
        job.join()
        job.cancel()
    }

    @Test
    fun `fetch album list - Error response`() = runTest {

        val errorMessage = "error occurred"
        `when`(
            iAlbumRepo.getTopAlbum()
        ).thenReturn(
            flowOf(Resource.Error(message = errorMessage))
        )
        albumListUseCase.getTopAlbums().
           collectLatest {
                assertTrue(it is Resource.Error)
                assertEquals(it.message, errorMessage)
            }
    }
}