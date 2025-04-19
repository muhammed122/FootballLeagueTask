package com.example.footballleaguetask.presentation.details

import com.example.ui.base.ViewAction

sealed class CompetitionDetailsAction: ViewAction {
    data class LoadCompetition(val id: Int) : CompetitionDetailsAction()
}