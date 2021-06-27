package com.linh.data.source.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.linh.data.source.local.api.PreferencesKeys.ACCESS_TOKEN
import com.linh.data.source.local.api.dataStore
import com.linh.domain.model.AccessToken
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AccessTokenWrapper constructor(
    val context: Context,
    val moshi: Moshi,
    val dispatcher: CoroutineDispatcher
) {

    suspend fun saveAccessToken(accessToken: AccessToken) {
        withContext(dispatcher) {
            context.dataStore.edit { settings ->
                settings[ACCESS_TOKEN] =
                    moshi.adapter(AccessToken::class.java).toJson(accessToken)
            }
        }
    }

    suspend fun getAccessToken(): Flow<AccessToken?> {
        return withContext(dispatcher) {
            context.dataStore.data.map {
                val token = it[ACCESS_TOKEN]
                token?.let {
                    return@map moshi.adapter(AccessToken::class.java)
                        .fromJson(token)
                } ?: kotlin.run {
                    return@map null
                }
            }
        }
    }
}