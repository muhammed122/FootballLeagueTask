package com.example.footballleaguetask.data.source.remote

import com.example.footballleaguetask.data.model.response.AreasResponseDto
import com.example.footballleaguetask.data.model.response.CompetitionDetailsResponseDto
import com.example.footballleaguetask.data.model.response.CompetitionsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("areas")
    suspend fun getAreas(): AreasResponseDto?

    @GET("competitions")
    suspend fun getCompetitions(
        @Query("areas") areas: String,
    ): CompetitionsResponseDto?

    @GET("competitions/{id}")
    suspend fun getCompetitionDetails(
        @Path("id") id: Int,
    ): CompetitionDetailsResponseDto?

}