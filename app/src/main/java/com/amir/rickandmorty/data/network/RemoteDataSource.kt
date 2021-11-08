package com.amir.rickandmorty.data.network

import com.amir.rickandmorty.base.BaseDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val apiService: RickAndMortyApiService) : BaseDataSource() {

    suspend fun fetchCharacters(page: Int) = getResult {
        apiService.fetchCharacters(page)
    }

    suspend fun fetchLocation(page: Int) = getResult {
        apiService.fetchLocation(page)
    }

    suspend fun fetchEpisode(page: Int) = getResult {
        apiService.fetchEpisode(page)
    }

    suspend fun fetchFilteredData(name: String) = getResult {
        apiService.fetchFilteredData(name)
    }

    suspend fun fetchFilteredDataLoc(name: String) = getResult {
        apiService.fetchFilteredDataLoc(name)
    }

    suspend fun fetchFilteredDataEpisode(name: String) = getResult {
        apiService.fetchFilteredDataEpisode(name)
    }

    suspend fun fetchCharactersId(id: Int) = getResult {
        apiService.fetchCharactersId(id)
    }

    suspend fun fetchLocationId(id: Int) = getResult {
        apiService.fetchLocationId(id)
    }
    suspend fun fetchEpisodeId(id: Int) = getResult {
        apiService.fetchEpisodeId(id)
    }
}