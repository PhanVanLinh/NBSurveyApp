package com.linh.data.source.local.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.linh.data.source.local.entity.SurveyEntity

@Database(entities = [SurveyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun surveyDao(): SurveyDao
}