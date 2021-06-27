package com.linh.data.source.local

import com.linh.data.source.local.api.SurveyDao
import com.linh.domain.repository.datasource.local.SurveyLocalDataSource
import javax.inject.Inject

class DefaultSurveyLocalDataSource @Inject constructor(
    private val surveyDao: SurveyDao
) : SurveyLocalDataSource {

}