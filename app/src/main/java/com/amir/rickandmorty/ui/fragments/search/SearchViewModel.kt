package com.amir.rickandmorty.ui.fragments.search

import androidx.lifecycle.LiveData
import com.amir.rickandmorty.base.BaseViewModel
import com.amir.rickandmorty.data.network.Resource
import com.amir.rickandmorty.models.Character
import com.amir.rickandmorty.data.repository.Repository
import com.amir.rickandmorty.models.MainResponse

class SearchViewModel(private val repository: Repository) : BaseViewModel() {

    fun fetchFilteredData(name: String): LiveData<Resource<MainResponse<Character>>>{
        return repository.fetchRickAndMortyApiFilteredData(name)
    }

    fun fetchFilteredDataLoc(name: String): LiveData<Resource<MainResponse<Character>>>{
        return repository.fetchRickAndMortyApiFilteredDataLoc(name)
    }

    fun fetchFilteredDataEpisodes(name: String): LiveData<Resource<MainResponse<Character>>>{
        return repository.fetchRickAndMortyApiFilteredDataEpisode(name)
    }
}