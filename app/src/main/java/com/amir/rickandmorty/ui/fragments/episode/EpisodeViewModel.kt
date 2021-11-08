package com.amir.rickandmorty.ui.fragments.episode

import androidx.lifecycle.LiveData
import com.amir.rickandmorty.base.BaseViewModel
import com.amir.rickandmorty.data.network.Resource
import com.amir.rickandmorty.data.repository.Repository
import com.amir.rickandmorty.models.Episodes
import com.amir.rickandmorty.models.MainResponse

class EpisodeViewModel(private val repository: Repository) : BaseViewModel() {

    fun fetchEpisodes(page: Int): LiveData<Resource<MainResponse<Episodes>>>{
        return repository.fetchRickAndMortyApiEpisode(page)
    }
}