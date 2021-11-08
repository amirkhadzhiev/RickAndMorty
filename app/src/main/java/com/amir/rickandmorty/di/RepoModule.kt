package com.amir.rickandmorty.di

import com.amir.rickandmorty.data.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModule: Module = module {
    single { Repository(get()) }
}