package com.linh.data.source.remote.model

import com.squareup.moshi.Json
import moe.banana.jsonapi2.JsonApi
import moe.banana.jsonapi2.Resource

@JsonApi(type = "token")
class AccessTokenData : Resource() {
    @Json(name = "access_token")
    var accessToken: String? = null

    @Json(name = "token_type")
    var tokenType: String? = null

    @Json(name = "expires_in")
    var expiresIn: Long? = null

    @Json(name = "refresh_token")
    var refreshToken: String? = null

    @Json(name = "created_at")
    var createdAt: Long? = null
}