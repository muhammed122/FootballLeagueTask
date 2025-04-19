package com.example.footballleaguetask.domain.usecase

import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.repository.Repository
import com.example.utils.coroutine_dispatcher.IoDispatcher
import com.example.utils.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAreasUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ): FlowUseCase<Unit, List<AreaModel>>(ioDispatcher){
    override fun execute(parameters: Unit): Flow<List<AreaModel>> =
         flow {
            emit(repository.getAreas())
        }
}