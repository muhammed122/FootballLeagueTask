package com.example.footballleaguetask.presentation.details

import com.example.footballleaguetask.domain.entity.CompetitionDetailsModel
import com.example.footballleaguetask.domain.entity.CompetitionModel
import com.example.ui.base.ViewState

data class CompetitionDetailsState(
    val competition: CompetitionDetailsModel? = null,
    override val isLoading: Boolean=false,
    override val error: String?=null,
) : ViewState