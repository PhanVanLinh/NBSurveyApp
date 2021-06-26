package com.linh.data.source.local.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linh.data.source.local.entity.SurveyEntity

@Dao
interface SurveyDao {

    @Query("SELECT * FROM surveys")
    fun getAll(): List<SurveyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<SurveyEntity>)

    @Query("DELETE FROM surveys")
    fun nukeTable()
}