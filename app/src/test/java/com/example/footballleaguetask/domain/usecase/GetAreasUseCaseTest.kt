package com.example.footballleaguetask.domain.usecase


import app.cash.turbine.test
import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.repository.Repository
import com.example.utils.network.Resource
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class GetAreasUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var useCase: GetAreasUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        repository = mock()
        useCase = GetAreasUseCase(repository, testDispatcher)
    }

    @Test
    fun `invoke should emit success when repository returns data`() = runTest(testDispatcher) {
        val expectedList = listOf(AreaModel(1, "Europe", "EUR"))
        `when`(repository.getAreas()).thenReturn(expectedList)

        useCase(Unit).test {
            val loading = awaitItem()
            assertTrue(loading is Resource.Loading)

            val result = awaitItem()
            assertTrue(result is Resource.Success)
            assertEquals(expectedList, (result as Resource.Success).value)

            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit failure when repository throws exception`() = runTest(testDispatcher) {
        val exception = RuntimeException("Network error")
        `when`(repository.getAreas()).thenThrow(exception)

        useCase(Unit).test {
            val loading = awaitItem()
            assertTrue(loading is Resource.Loading)
            val result = awaitItem()
            assertTrue(result is Resource.Failure)
            assertEquals("Network error", (result as Resource.Failure).exception.message)
            awaitComplete()
        }
    }
}

