package com.linh.androidnbsurveyapp.ui.feature.login

import app.cash.turbine.test
import com.linh.androidnbsurveyapp.R
import com.linh.androidnbsurveyapp.common.CoroutineTestRule
import com.linh.domain.interactor.LoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Mock
    lateinit var loginUseCase: LoginUseCase

    lateinit var viewModel: LoginViewModel

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @Before
    fun setUp() {
        viewModel = LoginViewModel(loginUseCase, coroutineRule.testDispatcher)
    }

    @Test
    fun `give valid email and password should return valid validation`() =
        coroutineRule.testDispatcher.runBlockingTest {
            viewModel.loginFormState.test {
                viewModel.loginDataChanged("a@gmail.com", "1234")
                assertEquals(expectItem().isDataValid, true)
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `give blank email and blank password should return blank error`() =
        coroutineRule.testDispatcher.runBlockingTest {
            viewModel.loginFormState.test {
                viewModel.loginDataChanged("", "")
                val item = expectItem()
                assertThat(
                    item.isDataValid, `is`(false)
                )
                assertThat(
                    item.emailError,
                    `is`(R.string.error_email_is_required)
                )
                assertThat(
                    item.passwordError,
                    `is`(R.string.error_password_is_required)
                )
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `give invalid email and invalid password should return blank error`() =
        coroutineRule.testDispatcher.runBlockingTest {
            viewModel.loginFormState.test {
                viewModel.loginDataChanged("abc", "12")
                val item = expectItem()

                assertThat(item.isDataValid, `is`(false))
                assertThat(
                    item.emailError,
                    `is`(R.string.error_invalid_email)
                )
                assertThat(
                    item.passwordError,
                    `is`(R.string.error_invalid_password_length)
                )
                cancelAndConsumeRemainingEvents()
            }
        }
}