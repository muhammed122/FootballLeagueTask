package com.example.footballleaguetask.domain.repository

import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.entity.CompetitionDetailsModel
import com.example.footballleaguetask.domain.entity.CompetitionModel

interface Repository {
   suspend fun getAreas(): List<AreaModel>
   suspend fun getCompetitions(id: Int): List<CompetitionModel>
   suspend fun getCompetitionDetails(id: Int): CompetitionDetailsModel?
}