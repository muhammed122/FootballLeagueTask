package com.example.footballleaguetask.data.source.datasource

import com.example.footballleaguetask.data.model.response.AreasItemDto
import com.example.footballleaguetask.data.model.response.CompetitionDetailsResponseDto
import com.example.footballleaguetask.data.model.response.CompetitionsItemDto

interface RemoteDataSource {
    suspend fun getAreas(): List<AreasItemDto>

    suspend fun getCompetitions(id: Int): List<CompetitionsItemDto>

    suspend fun getCompetitionDetails(id: Int): CompetitionDetailsResponseDto?
}