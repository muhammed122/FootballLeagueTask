package com.example.footballleaguetask.presentation.home

import com.example.ui.base.ViewAction
import com.example.ui.base.ViewEvent

sealed class HomeViewAction: ViewAction {
    data object LoadAreas : HomeViewAction()
    data class LoadCompetitions(val areaId: Int) : HomeViewAction()
    data class NavigateToCompetitonDetails(val id: Int) : HomeViewAction()

}