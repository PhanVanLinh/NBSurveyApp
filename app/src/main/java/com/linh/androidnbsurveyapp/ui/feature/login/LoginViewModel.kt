package com.linh.androidnbsurveyapp.ui.feature.login

import androidx.annotation.VisibleForTesting
import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.androidnbsurveyapp.R
import com.linh.androidnbsurveyapp.common.MAX_PASSWORD_LENGTH
import com.linh.androidnbsurveyapp.common.MIN_PASSWORD_LENGTH
import com.linh.data.di.qualifier.MainDispatcher
import com.linh.domain.base.Result
import com.linh.domain.interactor.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @MainDispatcher
    private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _loginForm = MutableSharedFlow<LoginFormState>()
    val loginFormState: SharedFlow<LoginFormState> = _loginForm

    private val _loginResult = MutableSharedFlow<LoginResult>()
    val loginResultState: SharedFlow<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.emit(LoginResult(loading = true))
            when (loginUseCase(LoginUseCase.Input(email, password))) {
                is Result.Success -> {
                    _loginResult.emit(LoginResult(success = true))
                }
                is Result.NetworkError -> {
                    _loginResult.emit(LoginResult(error = R.string.error_network))
                }
                is Result.UnknownError -> {
                    _loginResult.emit(LoginResult(error = R.string.error_invalid_email_or_password))
                }
            }
        }
    }

    fun loginDataChanged(email: String, password: String) {
        viewModelScope.launch(mainDispatcher) {
            val emailError = validEmail(email)
            val passwordError = validatePassword(password)

            if (emailError == null && passwordError == null) {
                _loginForm.emit(LoginFormState(isDataValid = true))
            } else {
                _loginForm.emit(
                    LoginFormState(
                        emailError = emailError,
                        passwordError = passwordError
                    )
                )
            }
        }
    }

    private fun validEmail(email: String): Int? {
        if (email.isBlank()) {
            return R.string.error_email_is_required
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.error_invalid_email
        }
        return null
    }

    private fun validatePassword(password: String): Int? {
        if (password.isBlank()) {
            return R.string.error_password_is_required
        }
        if (password.length < MIN_PASSWORD_LENGTH || password.length > MAX_PASSWORD_LENGTH) {
            return R.string.error_invalid_password_length
        }
        return null
    }
}