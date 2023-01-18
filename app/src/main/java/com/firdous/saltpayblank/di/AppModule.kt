package com.firdous.saltpayblank.di

import com.firdous.saltpayblank.domain.usecase.AlbumListUseCase
import com.firdous.saltpayblank.domain.usecase.FavouriteAlbumUseCase
import com.firdous.saltpayblank.domain.usecase.SearchAlbumUseCase
import com.firdous.saltpayblank.ui.AlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule  = module {
    factory{AlbumListUseCase(get())}
    factory { FavouriteAlbumUseCase(get()) }
    factory { SearchAlbumUseCase(get()) }
}
val viewModelModule = module {
    viewModel { AlbumViewModel(get(), get(), get()) }
}
