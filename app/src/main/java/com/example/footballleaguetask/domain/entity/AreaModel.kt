package com.example.footballleaguetask.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "areas")
data class AreaModel(
    @PrimaryKey val id: Int,
    val name: String,
    val code: String
)