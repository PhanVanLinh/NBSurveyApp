package com.linh.data.source.remote

import java.io.IOException
import com.linh.domain.base.Result

abstract class BaseRemoteDataSource {

    protected suspend fun <T> getResult(call: suspend () -> T): Result<T> {
        try {
            val response = call()
            return Result.Success(response)
        } catch (e: Exception) {
            if (e is IOException) {
                return Result.NetworkError
            }
            return Result.UnknownError(e)
        }
    }
}