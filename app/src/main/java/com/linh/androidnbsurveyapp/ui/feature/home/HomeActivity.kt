package com.linh.androidnbsurveyapp.ui.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.linh.androidnbsurveyapp.common.KEY_SURVEY_ID
import com.linh.androidnbsurveyapp.common.showErrorDialog
import com.linh.androidnbsurveyapp.databinding.ActivityHomeBinding
import com.linh.androidnbsurveyapp.ui.feature.home.adapter.SurveyAdapter
import com.linh.androidnbsurveyapp.ui.feature.surveydetail.SurveyDetailActivity
import com.linh.androidnbsurveyapp.ui.model.SurveyModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
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
        initEvents()
        observeViewModel()

        homeViewModel.getSurveyList(true)
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getSurveyList(false)
    }

    private fun initRecyclerView() {
        surveyAdapter = SurveyAdapter {
            surveyAdapter.currentList[it]
        }
        binding.recyclerSurveys.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerSurveys.adapter = surveyAdapter

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerSurveys)

    }

    private fun initEvents() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.getSurveyList(true)
        }

        binding.startSurvey.setOnClickListener {
            val firstVisibleItem =
                (binding.recyclerSurveys.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val currentSurvey = surveyAdapter.currentList[firstVisibleItem]

            val intent = Intent(this@HomeActivity, SurveyDetailActivity::class.java)
            intent.putExtra(KEY_SURVEY_ID, currentSurvey.id)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        homeViewModel.surveyListUiState.asLiveData().observe(this, Observer {
            val result = it ?: return@Observer

            if (result.loading == true) {
                if (!binding.swipeRefreshLayout.isRefreshing) {
                    binding.progressLoading.visibility = View.VISIBLE
                }
            } else {
                binding.progressLoading.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
            }

            if (result.error != null) {
                showErrorDialog(this@HomeActivity, getString(result.error))
                return@Observer
            }

            if (result.success != null) {
                submitData(result.success)
                return@Observer
            }
        })
    }

    private fun submitData(surveys: List<SurveyModel>) {
        surveyAdapter.submitList(surveys)
    }
}