package com.example.footballleaguetask.data.source.datasource

import com.example.footballleaguetask.data.model.response.AreasItemDto
import com.example.footballleaguetask.data.model.response.CompetitionDetailsResponseDto
import com.example.footballleaguetask.data.model.response.CompetitionsItemDto
import com.example.footballleaguetask.data.source.remote.ApiService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService): RemoteDataSource {
    override suspend fun getAreas(): List<AreasItemDto> {
        return apiService.getAreas()?.areas ?: emptyList()
    }

    override suspend fun getCompetitions(id: Int): List<CompetitionsItemDto> {
        return apiService.getCompetitions("$id")?.competitions ?: emptyList()
    }

    override suspend fun getCompetitionDetails(id: Int): CompetitionDetailsResponseDto? {
        return apiService.getCompetitionDetails(id)
    }
}