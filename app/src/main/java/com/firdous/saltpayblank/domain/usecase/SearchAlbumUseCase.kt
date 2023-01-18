package com.firdous.saltpayblank.domain.usecase

import com.firdous.saltpayblank.domain.repository.IAlbumRepo

class SearchAlbumUseCase (private val iAlbumRepo: IAlbumRepo) {
    suspend fun searchAlbum(text:String) = iAlbumRepo.searchAlbum(text)
}