package com.linh.data.di

import android.content.Context
import androidx.room.Room
import com.linh.data.source.local.api.AppDatabase
import com.linh.data.source.local.api.SurveyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "nb-survey-db"
        ).build()
    }

    @Provides
    fun provideSurveyDao(db: AppDatabase): SurveyDao {
        return db.surveyDao()
    }
}