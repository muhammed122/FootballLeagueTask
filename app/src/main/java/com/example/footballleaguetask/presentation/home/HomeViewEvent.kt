package com.example.footballleaguetask.presentation.home

import com.example.ui.base.ViewEvent

sealed class HomeViewEvent : ViewEvent {

    data class NavigateToCompetitionDetails(val id:Int) : HomeViewEvent()
    data object NoInternetConnection : HomeViewEvent()
}