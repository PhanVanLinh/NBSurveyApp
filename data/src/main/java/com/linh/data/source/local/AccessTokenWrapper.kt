package com.linh.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.linh.data.source.local.api.PreferencesKeys.ACCESS_TOKEN
import com.linh.data.source.remote.model.AccessTokenData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccessTokenWrapper(val dataStore: DataStore<Preferences>) {

    suspend fun saveAccessToken(accessToken: AccessTokenData) {
        dataStore.edit { settings ->
            // TODO
            settings[ACCESS_TOKEN] = ""
        }
    }

    suspend fun getAccessToken(): Flow<AccessTokenData?> {
        return dataStore.data.map {
            // TODO
            null
        }
    }
}