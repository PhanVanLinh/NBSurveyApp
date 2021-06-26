package com.linh.domain.base

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    object NetworkError : Result<Nothing>()
    data class UnknownError(val exception: Exception) : Result<Nothing>()
}