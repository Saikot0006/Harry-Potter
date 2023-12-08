package com.example.harrypotter.ui.repository

import com.example.harrypotter.apiService.ApiServices
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getCharacters() = apiServices.getCharacters()

    suspend fun getCharactersDetailsByID(id : String) = apiServices.getCharactersDetailsByID(id)
}