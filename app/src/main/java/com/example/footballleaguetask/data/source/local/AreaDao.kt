package com.example.footballleaguetask.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballleaguetask.domain.entity.AreaModel

@Dao
interface AreaDao {

    @Query("SELECT * FROM areas")
    suspend fun getAllAreas(): List<AreaModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreas(areas: List<AreaModel>)

    @Query("DELETE FROM areas")
    suspend fun clearAll()
}
