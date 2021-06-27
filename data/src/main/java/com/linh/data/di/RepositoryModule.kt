package com.linh.data.di

import com.linh.data.source.DefaultAuthRepository
import com.linh.data.source.DefaultSurveyRepository
import com.linh.data.source.DefaultUserRepository
import com.linh.data.source.local.DefaultAuthLocalDataSource
import com.linh.data.source.local.DefaultSurveyLocalDataSource
import com.linh.data.source.local.DefaultUserLocalDataSource
import com.linh.data.source.remote.DefaultAuthRemoteDataSource
import com.linh.data.source.remote.DefaultSurveyRemoteDataSource
import com.linh.data.source.remote.DefaultUserRemoteDataSource
import com.linh.domain.repository.AuthRepository
import com.linh.domain.repository.SurveyRepository
import com.linh.domain.repository.UserRepository
import com.linh.domain.repository.datasource.local.AuthLocalDataSource
import com.linh.domain.repository.datasource.local.SurveyLocalDataSource
import com.linh.domain.repository.datasource.local.UserLocalDataSource
import com.linh.domain.repository.datasource.remote.AuthRemoteDataSource
import com.linh.domain.repository.datasource.remote.SurveyRemoteDataSource
import com.linh.domain.repository.datasource.remote.UserRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthLocalDataSource(
        d: DefaultAuthLocalDataSource
    ): AuthLocalDataSource

    @Binds
    abstract fun bindAuthRemoteDataSource(
        d: DefaultAuthRemoteDataSource
    ): AuthRemoteDataSource

    @Binds
    abstract fun bindAuthRepository(
        d: DefaultAuthRepository
    ): AuthRepository

    @Binds
    abstract fun bindUserLocalDataSource(
        d: DefaultUserLocalDataSource
    ): UserLocalDataSource

    @Binds
    abstract fun bindUserRemoteDataSource(
        d: DefaultUserRemoteDataSource
    ): UserRemoteDataSource

    @Binds
    abstract fun bindUserRepository(
        d: DefaultUserRepository
    ): UserRepository

    @Binds
    abstract fun bindSurveyLocalDataSource(
        d: DefaultSurveyLocalDataSource
    ): SurveyLocalDataSource

    @Binds
    abstract fun bindSurveyRemoteDataSource(
        d: DefaultSurveyRemoteDataSource
    ): SurveyRemoteDataSource

    @Binds
    abstract fun bindSurveyRepository(
        d: DefaultSurveyRepository
    ): SurveyRepository

}