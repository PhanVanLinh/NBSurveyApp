package com.linh.androidnbsurveyapp.ui.model.mapper

import com.linh.androidnbsurveyapp.ui.model.SurveyModel
import com.linh.domain.model.Survey
import javax.inject.Inject

class SurveyModelMapper @Inject constructor() {

    fun surveyToSurveyModel(f: Survey): SurveyModel {
        return SurveyModel(
            id = f.id,
            title = f.title,
            coverImage = f.coverImage,
            description = f.description,
        )
    }
}