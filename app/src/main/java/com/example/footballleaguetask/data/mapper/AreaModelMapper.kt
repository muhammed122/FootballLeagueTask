package com.example.footballleaguetask.data.mapper

import com.example.footballleaguetask.data.model.response.AreaDto
import com.example.footballleaguetask.data.model.response.AreasItemDto
import com.example.footballleaguetask.domain.entity.AreaModel


fun AreasItemDto.mapToBusinessModel(): AreaModel =
    AreaModel(
        id = id ?: 0,
        name = name ?: "",
        code = countryCode ?: ""
    )