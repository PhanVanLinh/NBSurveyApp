package com.linh.androidnbsurveyapp.ui.feature.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.linh.androidnbsurveyapp.databinding.ActivityHomeBinding
import com.linh.androidnbsurveyapp.ui.feature.home.adapter.SurveyAdapter
import com.linh.androidnbsurveyapp.ui.model.SurveyModel


class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var surveyAdapter: SurveyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        surveyAdapter = SurveyAdapter {
            surveyAdapter.currentList[it]
        }
        binding.recyclerSurveys.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerSurveys.adapter = surveyAdapter

        surveyAdapter.submitList(
            listOf(
                SurveyModel("1", "2", "3"),
                SurveyModel("1", "2", "3"),
                SurveyModel("1", "2", "3")
            )
        )
    }
}