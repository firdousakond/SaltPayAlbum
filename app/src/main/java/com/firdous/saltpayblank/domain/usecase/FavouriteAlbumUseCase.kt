package com.firdous.saltpayblank.domain.usecase

import com.firdous.saltpayblank.domain.repository.IAlbumRepo

class FavouriteAlbumUseCase(private val albumRepo: IAlbumRepo) {
    suspend fun setFavourite(id:Int,isFavourite: Boolean) = albumRepo.setFavourite(id, isFavourite)
}