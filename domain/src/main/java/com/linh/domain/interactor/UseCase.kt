package com.linh.domain.interactor

import com.linh.domain.base.Result

abstract class UseCase<in I : UseCase.Input, out T : Any> {

    abstract suspend operator fun invoke(input: I): Result<T>

    abstract class Input
}