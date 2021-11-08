package com.amir.rickandmorty.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.amir.rickandmorty.data.network.*
import com.amir.rickandmorty.models.*
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {

    fun fetchRickAndMortyApiCharacters(page: Int): LiveData<Resource<MainResponse<Character>>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchCharacters(page)
        emit(response)
    }

    fun fetchRickAndMortyApiLocation(page: Int): LiveData<Resource<MainResponse<LocationModel>>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchLocation(page)
        emit(response)
    }

    fun fetchRickAndMortyApiEpisode(page: Int): LiveData<Resource<MainResponse<Episodes>>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchEpisode(page)
        emit(response)
    }

    fun fetchRickAndMortyApiFilteredData(name: String): LiveData<Resource<MainResponse<Character>>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchFilteredData(name)
        emit(response)
    }

    fun fetchRickAndMortyApiFilteredDataLoc(name: String): LiveData<Resource<MainResponse<Character>>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchFilteredDataLoc(name)
        emit(response)
    }

    fun fetchRickAndMortyApiFilteredDataEpisode(name: String): LiveData<Resource<MainResponse<Character>>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchFilteredDataEpisode(name)
        emit(response)
    }

    fun fetchRickAndMortyApiCharactersId(id: Int): LiveData<Resource<Character>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchCharactersId(id)
        emit(response)
    }

    fun fetchRickAndMortyApiLocationId(id: Int): LiveData<Resource<LocationModel>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchLocationId(id)
        emit(response)
    }
    fun fetchRickAndMortyApiEpisodeId(id: Int): LiveData<Resource<Episodes>> = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        val response = dataSource.fetchEpisodeId(id)
        emit(response)
    }

}