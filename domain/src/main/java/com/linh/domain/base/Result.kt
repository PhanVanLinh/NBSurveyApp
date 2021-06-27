package com.linh.domain.base

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    object NetworkError : Result<Nothing>()
    data class UnknownError(val exception: Exception) : Result<Nothing>()

    fun <T> errorMap(): Result<T> {
        return when (this) {
            is UnknownError -> {
                UnknownError(this.exception)
            }
            is NetworkError -> {
                NetworkError
            }
            else -> {
                throw java.lang.Exception("Can not map success result, must be error result")
            }
        }
    }
}