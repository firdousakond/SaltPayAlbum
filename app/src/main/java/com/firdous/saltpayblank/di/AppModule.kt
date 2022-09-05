package com.firdous.saltpayblank.di

import com.firdous.saltpayblank.domain.usecase.AlbumUseCase
import com.firdous.saltpayblank.ui.AlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule  = module {
    factory{AlbumUseCase(get())}
}
val viewModelModule = module {
    viewModel { AlbumViewModel(get()) }
}
