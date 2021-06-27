package com.linh.androidnbsurveyapp.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.androidnbsurveyapp.R
import com.linh.androidnbsurveyapp.ui.model.mapper.SurveyModelMapper
import com.linh.data.di.qualifier.MainDispatcher
import com.linh.domain.base.Result
import com.linh.domain.interactor.GetSurveyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurveyListUseCase: GetSurveyListUseCase,
    @MainDispatcher
    private val mainDispatcher: CoroutineDispatcher,
    private val surveyModelMapper: SurveyModelMapper
) : ViewModel() {

    private val _surveyListUiState = MutableSharedFlow<SurveyListUiState>()
    val surveyListUiState: SharedFlow<SurveyListUiState> = _surveyListUiState

    fun getSurveyList(isCacheDirty: Boolean = false) {
        viewModelScope.launch {
            _surveyListUiState.emit(SurveyListUiState(loading = true))
            val result = getSurveyListUseCase(
                GetSurveyListUseCase.Input(
                    pageNumber = 1,
                    pageSize = 4,
                    isCacheDirty = isCacheDirty,
                )
            )

            when (result) {
                is Result.Success -> {
                    _surveyListUiState.emit(
                        SurveyListUiState(success = result.data.map {
                            surveyModelMapper.surveyToSurveyModel(
                                it
                            )
                        })
                    )
                }
                is Result.NetworkError -> {
                    _surveyListUiState.emit(SurveyListUiState(error = R.string.error_network))
                }
                is Result.UnknownError -> {
                    _surveyListUiState.emit(SurveyListUiState(error = R.string.error_failed_to_fetch_survey_data))
                }
            }
        }
    }
}