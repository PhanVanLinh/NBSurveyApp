package com.linh.data.source.mapper

import com.linh.data.source.local.entity.SurveyEntity
import com.linh.data.source.remote.model.SurveyData
import com.linh.domain.model.Survey
import javax.inject.Inject

class SurveyDataMapper @Inject constructor() {

    fun surveyToSurveyData(f: Survey): SurveyData {
        return SurveyData().apply {
            id = f.id
            title = f.title
            coverImage = f.coverImage
            description = f.description
        }
    }

    fun surveyDataToSurvey(f: SurveyData): Survey {
        return Survey(
            id = f.id,
            title = f.title,
            coverImage = f.coverImage,
            description = f.description,
            questions = null
        )
    }

    fun surveyEntityToSurvey(f: SurveyEntity): Survey {
        return Survey(
            id = f.id,
            title = f.title,
            coverImage = f.coverImage,
            description = f.description,
            questions = null
        )
    }

    fun surveyToSurveyEntity(f: Survey): SurveyEntity {
        return SurveyEntity(
            id = f.id ?: "",
            title = f.title ?: "",
            coverImage = f.coverImage ?: "",
            description = f.description ?: "",
        )
    }
}