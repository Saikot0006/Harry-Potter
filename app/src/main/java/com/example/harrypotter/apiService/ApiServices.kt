package com.example.harrypotter.apiService

import com.example.harrypotter.models.CharacterDetailsResponse
import com.example.harrypotter.models.CharactersListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiServices {

    @GET("characters")
    suspend fun getCharacters() : Response<CharactersListResponse>
    @GET("character/{id}")
    suspend fun getCharactersDetailsByID(@Path("id") id : String) : Response<CharacterDetailsResponse>
}