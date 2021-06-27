package com.linh.androidnbsurveyapp.ui.feature.home

import com.linh.androidnbsurveyapp.ui.model.SurveyModel

class SurveyListUiState(
    val loading: Boolean? = null,
    val success: List<SurveyModel>? = null,
    val error: Int? = null
)