package com.amir.rickandmorty.di

import com.amir.rickandmorty.data.network.networkModule
import com.amir.rickandmorty.data.network.remoteDataSourceModule

val koinModules = listOf(
    networkModule,
    remoteDataSourceModule,
    repoModule,
    viewModules

)