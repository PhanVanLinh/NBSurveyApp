package com.linh.data.di

import android.content.Context
import androidx.room.Room
import com.linh.data.source.local.AccessTokenWrapper
import com.linh.data.source.local.api.AppDatabase
import com.linh.data.source.local.api.SurveyDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideSurveyDao(@ApplicationContext context: Context): SurveyDao {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "nb-survey-db"
        ).build()
        return db.surveyDao()
    }

    @Provides
    fun provideAccessTokenWrapper(@ApplicationContext context: Context): AccessTokenWrapper {
        return AccessTokenWrapper(context, Moshi.Builder().build(), Dispatchers.IO)
    }
}