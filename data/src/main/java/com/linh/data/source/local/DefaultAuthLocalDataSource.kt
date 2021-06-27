package com.linh.data.source.local

import com.linh.data.di.qualifier.IODispatcher
import com.linh.data.source.mapper.AccessTokenDataMapper
import com.linh.domain.base.Result
import com.linh.domain.model.AccessToken
import com.linh.domain.repository.datasource.local.AuthLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultAuthLocalDataSource @Inject constructor(
    private val accessTokenWrapper: AccessTokenWrapper,
    @IODispatcher
    private val dispatcher: CoroutineDispatcher,
    private val accessTokenDataMapper: AccessTokenDataMapper
) : AuthLocalDataSource {

    override suspend fun saveAccessToken(accessToken: AccessToken) {
        withContext(dispatcher) {
            accessTokenWrapper.saveAccessToken(accessToken)
        }
    }

    override suspend fun isLoggedIn(): Result<Boolean> {
        return Result.Success(accessTokenWrapper.getAccessToken().first() != null)
    }
}