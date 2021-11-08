package com.amir.rickandmorty.data.network


import com.amir.rickandmorty.models.*
import retrofit2.Response
import retrofit2.http.*


interface RickAndMortyApiService {

    @GET("/api/character")
    suspend fun fetchCharacters(@Query("page") page: Int): Response<MainResponse<Character>>

    @GET("/api/location")
    suspend fun fetchLocation(@Query("page") page: Int): Response<MainResponse<LocationModel>>

    @GET("/api/episode")
    suspend fun fetchEpisode(@Query("page") page: Int): Response<MainResponse<Episodes>>

    @GET("/api/character")
    suspend fun fetchFilteredData(@Query("name") name: String): Response<MainResponse<Character>>

    @GET("/api/location")
    suspend fun fetchFilteredDataLoc(@Query("name") name: String): Response<MainResponse<Character>>

    @GET("/api/episode")
    suspend fun fetchFilteredDataEpisode(@Query("name") name: String): Response<MainResponse<Character>>

    @GET("/api/character/{id}")
    suspend fun fetchCharactersId(@Path("id") postId: Int): Response<Character>

    @GET("/api/location/{id}")
    suspend fun fetchLocationId(@Path("id")id: Int): Response<LocationModel>

    @GET("/api/episode/{id}")
    suspend fun fetchEpisodeId(@Path("id") id: Int): Response<Episodes>
}