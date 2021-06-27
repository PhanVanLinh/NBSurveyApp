package com.linh.androidnbsurveyapp.ui.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.androidnbsurveyapp.R
import com.linh.data.di.qualifier.MainDispatcher
import com.linh.domain.base.Result
import com.linh.domain.interactor.CheckAuthStatusUseCase
import com.linh.domain.interactor.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase,
    @MainDispatcher
    private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _isLoggedInState = MutableSharedFlow<Boolean>()
    val isLoggedInState: SharedFlow<Boolean> = _isLoggedInState

    init {
        checkLoggedIn()
    }

    private fun checkLoggedIn() {
        viewModelScope.launch {
            val result = checkAuthStatusUseCase(CheckAuthStatusUseCase.Input)
            if (result is Result.Success) {
                _isLoggedInState.emit(result.data)
            }
        }
    }

}