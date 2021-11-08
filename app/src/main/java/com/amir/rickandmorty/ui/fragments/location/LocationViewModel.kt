package com.amir.rickandmorty.ui.fragments.location

import androidx.lifecycle.LiveData
import com.amir.rickandmorty.base.BaseViewModel
import com.amir.rickandmorty.data.network.Resource
import com.amir.rickandmorty.data.repository.Repository
import com.amir.rickandmorty.models.LocationModel
import com.amir.rickandmorty.models.MainResponse

class LocationViewModel(private val repository: Repository) : BaseViewModel() {

    fun fetchLocation(page: Int): LiveData<Resource<MainResponse<LocationModel>>>{
        return repository.fetchRickAndMortyApiLocation(page)
    }
}