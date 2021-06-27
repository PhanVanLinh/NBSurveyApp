package com.linh.androidnbsurveyapp.ui.feature.splash

import android.content.Intent
import android.os.Bundle
import com.linh.androidnbsurveyapp.R
import com.linh.androidnbsurveyapp.ui.BaseActivity
import com.linh.androidnbsurveyapp.ui.feature.login.LoginActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO handle logic to go to home or login
        startActivity(Intent(this, LoginActivity::class.java))
    }
}