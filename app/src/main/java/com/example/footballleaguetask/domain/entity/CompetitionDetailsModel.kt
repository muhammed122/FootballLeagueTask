package com.example.footballleaguetask.domain.entity

data class CompetitionDetailsModel(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String?,
    val area: AreaModel,
    val currentSeason: SeasonModel?
)
data class SeasonModel(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int?,
    val winner: WinnerModel?
)

data class WinnerModel(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String,
    val address: String,
    val website: String,
    val founded: Int,
    val clubColors: String,
    val venue: String
)