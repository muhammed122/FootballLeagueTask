package com.example.footballleaguetask.domain.usecase

import com.example.footballleaguetask.domain.entity.CompetitionModel
import com.example.footballleaguetask.domain.repository.Repository
import com.example.utils.coroutine_dispatcher.IoDispatcher
import com.example.utils.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCompetitionsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Int, List<CompetitionModel>>(ioDispatcher) {
    override fun execute(parameters: Int): Flow<List<CompetitionModel>> =
        flow {
            emit(repository.getCompetitions(parameters))
        }
}