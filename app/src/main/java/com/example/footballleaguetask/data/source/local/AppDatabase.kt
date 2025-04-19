package com.example.footballleaguetask.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.footballleaguetask.domain.entity.AreaModel

@Database(entities = [AreaModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun areaDao(): AreaDao
}
