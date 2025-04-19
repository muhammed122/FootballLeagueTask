package com.example.footballleaguetask.data.repository

import com.example.footballleaguetask.data.mapper.mapToBusinessModel
import com.example.footballleaguetask.data.source.datasource.FakeRemoteDataSource
import com.example.footballleaguetask.data.source.datasource.RemoteDataSource
import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.entity.CompetitionDetailsModel
import com.example.footballleaguetask.domain.entity.CompetitionModel
import com.example.footballleaguetask.domain.repository.Repository
import com.example.footballleaguetask.utils.FakeNetworkChecker
import com.example.utils.network.NetworkChecker
import javax.inject.Inject

class FakeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val networkChecker: FakeNetworkChecker
) : Repository {

    // Local cache (in-memory only, no Room needed)
    var localCache: List<AreaModel> = emptyList()

    override suspend fun getAreas(): List<AreaModel> {
        return if (networkChecker.isConnected()) {
            val remoteAreas = remoteDataSource.getAreas().map { it.mapToBusinessModel() }
            localCache = remoteAreas
            remoteAreas
        } else {
            localCache
        }
    }

    override suspend fun getCompetitions(id: Int): List<CompetitionModel> {
        return remoteDataSource.getCompetitions(id).map { it.mapToBusinessModel() }
    }

    override suspend fun getCompetitionDetails(id: Int): CompetitionDetailsModel? {
        return remoteDataSource.getCompetitionDetails(id)?.mapToBusinessModel()
    }
}