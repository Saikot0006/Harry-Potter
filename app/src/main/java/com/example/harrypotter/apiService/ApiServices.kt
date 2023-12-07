package com.example.harrypotter.apiService

import com.example.harrypotter.models.CharactersListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("characters")
    suspend fun getCharacters() : Response<CharactersListResponse>
}