package com.linh.androidnbsurveyapp.ui.feature.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.linh.androidnbsurveyapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<LoginActivity>
            = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun loginWithCorrectEmailAndPassword() {
        onView(withId(R.id.edt_email))
            .perform(replaceText("dev@nimblehq.co"), closeSoftKeyboard())
        onView(withId(R.id.edt_password))
            .perform(replaceText("12345678"), closeSoftKeyboard())
        onView(withId(R.id.button_login)).perform(click())

        Thread.sleep(5000)
    }

    @Test
    fun loginWithInvalidEmailAndPasswordShouldShowErrorDialog() {
        Thread.sleep(1000)
        onView(withId(R.id.edt_email))
            .perform(replaceText("dev@nimblehq.co"), closeSoftKeyboard())
        onView(withId(R.id.edt_password))
            .perform(replaceText("1234"), closeSoftKeyboard())
        Thread.sleep(500)
        onView(withId(R.id.button_login)).perform(click())

        Thread.sleep(5000)
    }
}