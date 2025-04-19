package com.example.footballleaguetask.data.repository

import com.example.footballleaguetask.data.mapper.mapToBusinessModel
import com.example.footballleaguetask.data.source.datasource.RemoteDataSource
import com.example.footballleaguetask.data.source.local.AreaDao
import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.entity.CompetitionDetailsModel
import com.example.footballleaguetask.domain.entity.CompetitionModel
import com.example.footballleaguetask.domain.repository.Repository
import com.example.utils.network.NetworkChecker
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: AreaDao,
    private val networkChecker: NetworkChecker
): Repository {

    override suspend fun getAreas(): List<AreaModel> {
        return if (networkChecker.isConnected()) {
            val remoteAreas = remoteDataSource.getAreas()
            localDataSource.clearAll()
            localDataSource.insertAreas(remoteAreas.map { it.mapToBusinessModel() })
            remoteAreas.map { it.mapToBusinessModel() }
        } else {
            localDataSource.getAllAreas()
        }
    }

    override suspend fun getCompetitions(id: Int): List<CompetitionModel> {
        return remoteDataSource.getCompetitions(id).map { it.mapToBusinessModel() }
    }

    override suspend fun getCompetitionDetails(id: Int): CompetitionDetailsModel? {
        return  remoteDataSource.getCompetitionDetails(id)?.mapToBusinessModel()
    }
}