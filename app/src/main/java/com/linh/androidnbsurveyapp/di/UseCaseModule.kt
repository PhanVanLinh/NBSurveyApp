package com.example.androidgrocery.di

import com.linh.domain.interactor.*
import com.linh.domain.repository.AuthRepository
import com.linh.domain.repository.SurveyRepository
import com.linh.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun bindLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    fun bindLogoutUseCase(
        authRepository: AuthRepository
    ): LogoutUseCase {
        return LogoutUseCase(authRepository)
    }

    @Provides
    fun bindCheckAuthStatusUseCase(
        authRepository: AuthRepository
    ): CheckAuthStatusUseCase {
        return CheckAuthStatusUseCase(authRepository)
    }

    @Provides
    fun bindForgotPasswordUseCase(
        authRepository: AuthRepository
    ): ForgotPasswordUseCase {
        return ForgotPasswordUseCase(authRepository)
    }

    @Provides
    fun bindGetProfileUseCase(
        userRepository: UserRepository
    ): GetProfileUseCase {
        return GetProfileUseCase(userRepository)
    }

    @Provides
    fun bindGetSurveyListUseCase(
        surveyRepository: SurveyRepository
    ): GetSurveyListUseCase {
        return GetSurveyListUseCase(surveyRepository)
    }

    @Provides
    fun bindGetSurveyDetailUseCase(
        surveyRepository: SurveyRepository
    ): GetSurveyDetailUseCase {
        return GetSurveyDetailUseCase(surveyRepository)
    }
}