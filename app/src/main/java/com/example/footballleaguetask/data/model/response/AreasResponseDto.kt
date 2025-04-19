package com.example.footballleaguetask.data.model.response

import com.google.gson.annotations.SerializedName

data class AreasResponseDto(
	@SerializedName("count") val count: Int? = null,
	@SerializedName("areas") val areas: List<AreasItemDto>? = null,
)
data class AreasItemDto(
	@SerializedName("flag") val flag: String? = null,
	@SerializedName("countryCode") val countryCode: String? = null,
	@SerializedName("parentArea") val parentArea: String? = null,
	@SerializedName("name") val name: String? = null,
	@SerializedName("id") val id: Int? = null,
	@SerializedName("parentAreaId") val parentAreaId: Int? = null
)

