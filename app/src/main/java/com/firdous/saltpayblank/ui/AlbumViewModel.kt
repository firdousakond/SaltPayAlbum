package com.firdous.saltpayblank.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.local.entity.AlbumEntity
import com.firdous.saltpayblank.domain.usecase.AlbumListUseCase
import com.firdous.saltpayblank.domain.usecase.FavouriteAlbumUseCase
import com.firdous.saltpayblank.domain.usecase.SearchAlbumUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumViewModel(
    private val useCase: AlbumListUseCase,
    private val favouriteAlbumUseCase: FavouriteAlbumUseCase,
    private val searchAlbumUseCase: SearchAlbumUseCase
) : ViewModel() {

    private val _albumStateFlow = MutableStateFlow<Resource<List<AlbumEntity>>>(Resource.Loading)
    val albumStateFlow = _albumStateFlow.asStateFlow()

    init {
        getTopAlbums()
    }

    fun getTopAlbums() {
        viewModelScope.launch {
            useCase.getTopAlbums()
                .catch {
                    Timber.e(it.message)
                    _albumStateFlow.emit(Resource.Error(it.message ?: ""))
                }.collect {
                    _albumStateFlow.emit(it)
                }
        }
    }

    fun setFavourite(album: AlbumEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteAlbumUseCase.setFavourite(album.id, album.favourite ?: false)
        }
    }

    fun searchAlbum(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchAlbumUseCase.searchAlbum(text).collectLatest {
                _albumStateFlow.update { album ->
                    if (album is Resource.Success) {
                        Resource.Success(it)
                    } else {
                        album
                    }
                }
            }
        }

    }

}