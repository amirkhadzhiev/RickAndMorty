package com.amir.rickandmorty.ui.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amir.rickandmorty.base.BaseViewModel
import com.amir.rickandmorty.data.network.Resource
import com.amir.rickandmorty.data.repository.Repository
import com.amir.rickandmorty.models.*

class DetailViewModel(private val repository: Repository) : BaseViewModel() {

    fun fetchCharactersId(id: Int): LiveData<Resource<Character>>{
        return repository.fetchRickAndMortyApiCharactersId(id)
    }

    fun fetchLocationId(id: Int): LiveData<Resource<LocationModel>>{
        return repository.fetchRickAndMortyApiLocationId(id)
    }

    fun fetchEpisodesId(id: Int): LiveData<Resource<Episodes>>{
        return repository.fetchRickAndMortyApiEpisodeId(id)
    }

}