package com.example.footballleaguetask.data.mapper

import com.example.footballleaguetask.data.model.response.AreaDto
import com.example.footballleaguetask.data.model.response.CompetitionDetailsResponseDto
import com.example.footballleaguetask.data.model.response.CurrentSeasonDto
import com.example.footballleaguetask.data.model.response.WinnerDto
import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.entity.CompetitionDetailsModel
import com.example.footballleaguetask.domain.entity.SeasonModel
import com.example.footballleaguetask.domain.entity.WinnerModel

fun CompetitionDetailsResponseDto.mapToBusinessModel(): CompetitionDetailsModel {
    return CompetitionDetailsModel(
        id = id ?: -1,
        name = name.orEmpty(),
        code = code.orEmpty(),
        type = type.orEmpty(),
        emblem = emblem,
        area = area?.mapToBusinessModel() ?: AreaModel(-1, "", ""),
        currentSeason = currentSeason?.mapToBusinessModel()
    )
}

fun AreaDto.mapToBusinessModel(): AreaModel {
    return AreaModel(
        id = id ?: -1,
        name = name.orEmpty(),
        code = code?:"",
    )
}

fun CurrentSeasonDto.mapToBusinessModel(): SeasonModel {
    return SeasonModel(
        id = id ?: -1,
        startDate = startDate.orEmpty(),
        endDate = endDate.orEmpty(),
        currentMatchday = currentMatchday,
        winner = winner?.mapToBusinessModel()
    )
}

fun WinnerDto.mapToBusinessModel(): WinnerModel {
    return WinnerModel(
        id = id ?: -1,
        name = name.orEmpty(),
        shortName = shortName.orEmpty(),
        tla = tla.orEmpty(),
        crest = crest.orEmpty(),
        address = address.orEmpty(),
        website = website.orEmpty(),
        founded = founded ?: 0,
        clubColors = clubColors.orEmpty(),
        venue = venue.orEmpty()
    )
}
