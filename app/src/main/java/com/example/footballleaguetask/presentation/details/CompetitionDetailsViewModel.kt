package com.example.footballleaguetask.presentation.details

import androidx.lifecycle.viewModelScope
import com.example.footballleaguetask.domain.usecase.GetCompetitionDetailsUseCase
import com.example.ui.base.BaseViewModel
import com.example.ui.base.ViewEvent
import com.example.utils.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CompetitionDetailsViewModel @Inject constructor(
    private val getCompetitionUseCase: GetCompetitionDetailsUseCase
) : BaseViewModel<CompetitionDetailsState, ViewEvent, CompetitionDetailsAction>(
    CompetitionDetailsState()
) {
    override fun handleAction(action: CompetitionDetailsAction) {
        when (action) {
            is CompetitionDetailsAction.LoadCompetition -> {
                getCompetition(action.id)
            }
        }
    }

    private fun getCompetition(id: Int) {
        getCompetitionUseCase(id)
            .onEach {
                when (it) {
                    is Resource.Success -> setState { copy(competition = it.value, isLoading = false) }
                    is Resource.Failure -> setState { copy(error = it.exception.message, isLoading = false) }
                    is Resource.Loading -> setState { copy(isLoading = true) }
                    Resource.Empty -> {}
                }
            }.launchIn(viewModelScope)
    }
}