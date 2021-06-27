package com.linh.data.source.remote.model

import com.squareup.moshi.Json
import moe.banana.jsonapi2.JsonApi
import moe.banana.jsonapi2.Resource

@JsonApi(type = "survey")
class SurveyData : Resource() {
    var title: String? = null

    @Json(name = "cover_image_url")
    var coverImage: String? = null
    var description: String? = null
}