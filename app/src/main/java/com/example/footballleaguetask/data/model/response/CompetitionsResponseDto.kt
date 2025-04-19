package com.example.footballleaguetask.data.model.response

import com.google.gson.annotations.SerializedName

data class CompetitionsResponseDto(
	@SerializedName("count") val count: Int? = null,
	@SerializedName("competitions") val competitions: List<CompetitionsItemDto>? = null,
)

data class AreaDto(
	@SerializedName("code") val code: String? = null,
	@SerializedName("flag") val flag: String? = null,
	@SerializedName("name") val name: String? = null,
	@SerializedName("id") val id: Int? = null
)

data class CompetitionsItemDto(
	@SerializedName("area") val area: AreaDto? = null,
	@SerializedName("lastUpdated") val lastUpdated: String? = null,
	@SerializedName("code") val code: String? = null,
	@SerializedName("currentSeason") val currentSeason: CurrentSeasonDto? = null,
	@SerializedName("name") val name: String? = null,
	@SerializedName("id") val id: Int? = null,
	@SerializedName("numberOfAvailableSeasons") val numberOfAvailableSeasons: Int? = null,
	@SerializedName("type") val type: String? = null,
	@SerializedName("emblem") val emblem: String? = null,
	@SerializedName("plan") val plan: String? = null
)

data class WinnerDto(
	@SerializedName("venue") val venue: String? = null,
	@SerializedName("lastUpdated") val lastUpdated: String? = null,
	@SerializedName("website") val website: String? = null,
	@SerializedName("address") val address: String? = null,
	@SerializedName("clubColors") val clubColors: String? = null,
	@SerializedName("name") val name: String? = null,
	@SerializedName("tla") val tla: String? = null,
	@SerializedName("founded") val founded: Int? = null,
	@SerializedName("id") val id: Int? = null,
	@SerializedName("shortName") val shortName: String? = null,
	@SerializedName("crest") val crest: String? = null
)


data class CurrentSeasonDto(
	@SerializedName("winner") val winner: WinnerDto? = null,
	@SerializedName("currentMatchday") val currentMatchday: Int? = null,
	@SerializedName("endDate") val endDate: String? = null,
	@SerializedName("id") val id: Int? = null,
	@SerializedName("startDate") val startDate: String? = null
)
