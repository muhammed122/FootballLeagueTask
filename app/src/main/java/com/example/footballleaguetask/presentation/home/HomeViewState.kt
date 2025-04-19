package com.example.footballleaguetask.presentation.home

import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.entity.CompetitionModel
import com.example.ui.base.ViewState

data class HomeViewState(
    val areas : List<AreaModel> = emptyList(),
    val competitions : Map<Int, List<CompetitionModel>> = emptyMap(),
    val expandedAreaIds: Set<Int> = emptySet(),
    override val isLoading: Boolean=false,
    override val error: String?=null
) : ViewState
