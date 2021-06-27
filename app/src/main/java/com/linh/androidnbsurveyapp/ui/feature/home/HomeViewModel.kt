package com.linh.androidnbsurveyapp.ui.feature.home

import androidx.lifecycle.ViewModel
import com.linh.data.di.qualifier.MainDispatcher
import com.linh.domain.interactor.GetSurveyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurveyListUseCase: GetSurveyListUseCase,
    @MainDispatcher
    private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {


}