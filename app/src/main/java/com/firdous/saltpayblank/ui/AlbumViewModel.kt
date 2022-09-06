package com.firdous.saltpayblank.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firdous.saltpayblank.data.Resource
import com.firdous.saltpayblank.data.model.AlbumsResponse
import com.firdous.saltpayblank.domain.usecase.AlbumUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumViewModel(private val useCase: AlbumUseCase) : ViewModel() {
    private val _albumStateFlow = MutableStateFlow<Resource<AlbumsResponse>>(Resource.Loading)
    val albumStateFlow = _albumStateFlow.asStateFlow()

    init {
        getTopAlbums()
    }
    private fun getTopAlbums() {
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

}