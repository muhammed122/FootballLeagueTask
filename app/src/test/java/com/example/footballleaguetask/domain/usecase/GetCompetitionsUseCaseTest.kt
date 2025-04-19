package com.example.footballleaguetask.domain.usecase

import app.cash.turbine.test
import com.example.footballleaguetask.domain.entity.CompetitionModel
import com.example.footballleaguetask.domain.repository.Repository
import com.example.utils.network.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)

class GetCompetitionsUseCaseTest{

    private lateinit var repository: Repository
    private lateinit var useCase: GetCompetitionsUseCase

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup(){
        repository = mock()
        useCase = GetCompetitionsUseCase(repository, testDispatcher)
    }

    @Test
    fun `invoke should return list of competitions`() = runTest(testDispatcher){
        val expectedList = listOf(CompetitionModel(1, "UEFA Champions League", "CL", "CUP"))
        `when`(repository.getCompetitions(1)).thenReturn(expectedList)

        useCase(1).test{
            val loading = awaitItem()
            assertTrue(loading is Resource.Loading)
            val result = awaitItem()
            assertEquals(expectedList, (result as Resource.Success).value)
            awaitComplete()
        }

    }

}