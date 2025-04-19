package com.example.footballleaguetask.data.mapper

import com.example.footballleaguetask.data.model.response.CompetitionsItemDto
import com.example.footballleaguetask.domain.entity.CompetitionModel


fun CompetitionsItemDto.mapToBusinessModel(): CompetitionModel =
    CompetitionModel(
        id = this.id ?: 0,
        flag = this.emblem ?: "",
        name = this.name ?: "",
        type = this.type ?: ""
    )