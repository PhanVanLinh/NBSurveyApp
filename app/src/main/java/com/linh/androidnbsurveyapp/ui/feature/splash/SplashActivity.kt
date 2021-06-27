package com.linh.androidnbsurveyapp.ui.feature.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import com.linh.androidnbsurveyapp.R
import com.linh.androidnbsurveyapp.ui.BaseActivity
import com.linh.androidnbsurveyapp.ui.feature.home.HomeActivity
import com.linh.androidnbsurveyapp.ui.feature.home.HomeViewModel
import com.linh.androidnbsurveyapp.ui.feature.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        observeViewModel()
    }

    private fun observeViewModel() {
        splashViewModel.isLoggedInState.asLiveData().observe(this, { isLoggedIn ->
            if (isLoggedIn) {
                moveToHomeScreen()
            } else {
                moveToLoginScreen()
            }
        })
    }

    private fun moveToLoginScreen() {
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
        this.overridePendingTransition(0, 0)
    }

    private fun moveToHomeScreen() {
        finish()
        startActivity(Intent(this, HomeActivity::class.java))
        this.overridePendingTransition(0, 0)
    }
}