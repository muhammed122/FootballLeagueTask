package com.example.footballleaguetask.data.model.response

import com.google.gson.annotations.SerializedName

data class CompetitionDetailsResponseDto(
	@SerializedName("area") val area: AreaDto? = null,
	@SerializedName("lastUpdated") val lastUpdated: String? = null,
	@SerializedName("seasons") val seasons: List<CurrentSeasonDto>? = null,
	@SerializedName("code") val code: String? = null,
	@SerializedName("currentSeason") val currentSeason: CurrentSeasonDto? = null,
	@SerializedName("name") val name: String? = null,
	@SerializedName("id") val id: Int? = null,
	@SerializedName("type") val type: String? = null,
	@SerializedName("emblem") val emblem: String? = null
)



