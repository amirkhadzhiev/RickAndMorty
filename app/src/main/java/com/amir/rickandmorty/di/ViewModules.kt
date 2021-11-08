package com.amir.rickandmorty.di

import com.amir.rickandmorty.ui.fragments.character.CharacterViewModel
import com.amir.rickandmorty.ui.fragments.detail.DetailViewModel
import com.amir.rickandmorty.ui.fragments.episode.EpisodeViewModel
import com.amir.rickandmorty.ui.fragments.location.LocationViewModel
import com.amir.rickandmorty.ui.fragments.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}