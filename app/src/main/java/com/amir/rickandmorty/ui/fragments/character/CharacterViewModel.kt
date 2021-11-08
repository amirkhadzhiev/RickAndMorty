package com.amir.rickandmorty.ui.fragments.character

import androidx.lifecycle.LiveData
import com.amir.rickandmorty.base.BaseViewModel
import com.amir.rickandmorty.data.network.Resource
import com.amir.rickandmorty.data.repository.Repository
import com.amir.rickandmorty.models.Character
import com.amir.rickandmorty.models.MainResponse

class CharacterViewModel(private val repository: Repository) : BaseViewModel() {

    fun fetchCharacters(page: Int): LiveData<Resource<MainResponse<Character>>>{
        return repository.fetchRickAndMortyApiCharacters(page)
    }
}