package es.david.marvel.di

import es.david.marvel.data.mapper.DetailMapper
import es.david.marvel.data.repository.DetailRepository
import es.david.marvel.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val detailModule = module {

    viewModel { DetailViewModel(get()) }
    single { DetailRepository(get(), get()) }
    single { DetailMapper() }

}