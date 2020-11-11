package es.david.marvel.di

import es.david.marvel.data.repository.CharactersRepository
import es.david.marvel.ui.characters.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val charactersModule = module {

    viewModel { CharactersViewModel(get()) }
    single { CharactersRepository(get()) }

}