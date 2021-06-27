package com.linh.androidnbsurveyapp.ui.model

data class SurveyModel(
    val id: String?,
    val title: String?,
    val coverImage: String?,
    val description: String?
) {
    fun highQualityCoverImage(): String {
        return coverImage + "l";
    }
}