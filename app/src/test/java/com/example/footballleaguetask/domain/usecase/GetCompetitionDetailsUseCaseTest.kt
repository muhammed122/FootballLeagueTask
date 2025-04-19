package com.example.footballleaguetask.domain.usecase

import app.cash.turbine.test
import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.entity.CompetitionDetailsModel
import com.example.footballleaguetask.domain.entity.SeasonModel
import com.example.footballleaguetask.domain.entity.WinnerModel
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
class GetCompetitionDetailsUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var useCase: GetCompetitionDetailsUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        repository = mock()
        useCase = GetCompetitionDetailsUseCase(repository, testDispatcher)
    }

    @Test
    fun `invoke should emit success when repository returns data`() = runTest(testDispatcher){
        val expectedData = CompetitionDetailsModel(
            id = 1,
            name = "UEFA Champions League",
            code = "CL",
            type = "CUP",
            emblem = "https://crests.football-data.org/CL.png",
            area = AreaModel(
                id = 2077,
                name = "Europe",
                code = "EUR",
            ),
            currentSeason = SeasonModel(
                id = 2350,
                startDate = "2024-09-17",
                endDate = "2025-05-31",
                currentMatchday = 2,
                winner = WinnerModel(
                    id = 86,
                    name = "Real Madrid CF",
                    shortName = "Real Madrid",
                    tla = "RMA",
                    crest = "https://crests.football-data.org/86.png",
                    address = "Avenida Concha Espina, 1 Madrid 28036",
                    website = "http://www.realmadrid.com",
                    founded = 1902,
                    clubColors = "White / Purple",
                    venue = "Estadio Santiago Bernabéu"
                )
            )
        )

        `when`(repository.getCompetitionDetails(1)).thenReturn(expectedData)

        useCase(1).test{
            val loading = awaitItem()
            assertTrue(loading is Resource.Loading)
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            assertEquals(expectedData, (result as Resource.Success).value)
            awaitComplete()
        }
    }
}