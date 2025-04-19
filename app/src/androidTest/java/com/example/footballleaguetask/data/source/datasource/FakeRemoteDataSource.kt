package com.example.footballleaguetask.data.source.datasource

import com.example.footballleaguetask.data.model.response.AreasItemDto
import com.example.footballleaguetask.data.model.response.CompetitionDetailsResponseDto
import com.example.footballleaguetask.data.model.response.CompetitionsItemDto

class FakeRemoteDataSource : RemoteDataSource {
    override suspend fun getAreas(): List<AreasItemDto> {
        return listOf(
            AreasItemDto(id = 2077, name = "Europe", countryCode = "EUR")
        )
    }

    override suspend fun getCompetitions(id: Int): List<CompetitionsItemDto> {
        return listOf(
            CompetitionsItemDto(id = 2001, name = "UEFA Champions League", type = "CUP", emblem = "")
        )
    }

    override suspend fun getCompetitionDetails(id: Int): CompetitionDetailsResponseDto? {
        return null
    }
}
