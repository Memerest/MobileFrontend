package com.example.memerestcompose.ui.weekly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memerestcompose.data.FeedRepository
import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.models.MemeListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyFeedViewModel @Inject constructor(private val repository: FeedRepository) :
    ViewModel() {
    private val _uiState = MutableLiveData<UiState<List<MemeListUiModel>>>(UiState.Idle())
    val uiState: LiveData<UiState<List<MemeListUiModel>>> = _uiState

    fun fetchCollections() {
        _uiState.value = UiState.Loading()
        viewModelScope.launch {
            when (val loginData = repository.fetchWeekly()) {
                is DataResult.GenericError -> _uiState.value = UiState.Failure(loginData.error)
                is DataResult.Success -> _uiState.value = UiState.Success(loginData.data.map {
                    MemeListUiModel(
                        it.pictureId,
                        it.pictureUrl,
                        it.liked
                    )
                })
                is DataResult.NetworkError -> _uiState.value = UiState.Failure("Network error!")
            }
        }
    }
}