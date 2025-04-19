package com.example.footballleaguetask.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.footballleaguetask.domain.usecase.GetAreasUseCase
import com.example.footballleaguetask.domain.usecase.GetCompetitionsUseCase
import com.example.ui.base.BaseViewModel
import com.example.ui.base.ViewEvent
import com.example.utils.network.NetworkChecker
import com.example.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel @Inject constructor(
    private val getAreasUseCase: GetAreasUseCase,
    private val getCompetitionsUseCase: GetCompetitionsUseCase,
    private val networkChecker: NetworkChecker,
    ) : BaseViewModel<HomeViewState, HomeViewEvent, HomeViewAction>(
    HomeViewState()
) {

    init {
        if (!networkChecker.isConnected()) {
            setEvent { HomeViewEvent.NoInternetConnection }
        }
        handleAction(HomeViewAction.LoadAreas)
    }

    override fun handleAction(action: HomeViewAction) {
        when (action) {
            HomeViewAction.LoadAreas -> getAreas()
            is HomeViewAction.LoadCompetitions -> getCompetitions(action.areaId)
            is HomeViewAction.NavigateToCompetitonDetails -> setEvent {
                HomeViewEvent.NavigateToCompetitionDetails(
                    action.id
                )
            }
        }
    }

    fun toggleExpandedArea(areaId: Int) {
        setState {
            val isExpanded = areaId in expandedAreaIds
            copy(
                expandedAreaIds = if (isExpanded)
                    expandedAreaIds - areaId
                else
                    expandedAreaIds + areaId
            )
        }
    }

    fun clearError() {
        setState { copy(error = null) }
    }

    private fun getAreas() {
        getAreasUseCase.invoke(Unit)
            .onEach {
                when (it) {
                    is Resource.Success -> {
                        setState { copy(isLoading = false, areas = it.value) }
                    }

                    is Resource.Failure -> {
                        setState { copy(isLoading = false, error = it.exception.localizedMessage) }
                    }

                    is Resource.Loading -> {
                        setState { copy(isLoading = true) }
                    }

                    Resource.Empty -> {}
                }
            }.launchIn(viewModelScope)
    }

    private fun getCompetitions(areaId: Int) {
        getCompetitionsUseCase.invoke(areaId)
            .onEach {
                when (it) {
                    is Resource.Success -> {
                        setState {
                            val updatedMap = competitions.toMutableMap()
                            updatedMap[areaId] = it.value
                            copy(
                                isLoading = false,
                                competitions = updatedMap
                            )
                        }
                        Log.d("Competitions", "Loaded for areaId $areaId: ${it.value}")

                    }

                    is Resource.Failure -> {
                        setState { copy(isLoading = false, error = it.exception.localizedMessage) }
                    }

                    is Resource.Loading -> {
                        setState { copy(isLoading = true) }
                    }

                    Resource.Empty -> {}
                }
            }.launchIn(viewModelScope)
    }
}