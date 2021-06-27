package com.linh.androidnbsurveyapp.ui.feature.surveydetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linh.androidnbsurveyapp.common.KEY_SURVEY_ID
import com.linh.androidnbsurveyapp.databinding.ActivitySurveyDetailBinding

class SurveyDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurveyDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val surveyId = intent.getStringExtra(KEY_SURVEY_ID)
        binding.textSurveyId.text = surveyId
    }
}