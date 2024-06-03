package com.example.memerestcompose.ui.collections.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memerestcompose.data.model.DataResult
import com.example.memerestcompose.data.repository.CollectionRepository
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.models.CollectionUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionListViewModel @Inject constructor(private val feedRepository: CollectionRepository) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<CollectionUIModel>>>(UiState.Idle())
    val uiState: LiveData<UiState<List<CollectionUIModel>>> = _uiState

    private val _uiStateAdd = MutableLiveData<UiState<Unit>>(UiState.Idle())
    val uiStateAdd: LiveData<UiState<Unit>> = _uiStateAdd

    private val _uiStateCreate = MutableLiveData<UiState<Unit>>(UiState.Idle())
    val uiStateCreate: LiveData<UiState<Unit>> = _uiStateCreate

    fun fetchCollections() {
        _uiState.value = UiState.Loading()
        viewModelScope.launch {
            when (val collections = feedRepository.fetchCollections()) {
                is DataResult.GenericError -> _uiState.value = UiState.Failure(collections.error)
                is DataResult.Success -> _uiState.value = UiState.Success(collections.data.map {
                    if (it.pictures.isEmpty()) {
                        CollectionUIModel(
                            it.collectionId, it.name, ""
                        )
                    } else {
                        CollectionUIModel(
                            it.collectionId, it.name, it.pictures[0].pictureUrl
                        )
                    }
                })

                is DataResult.NetworkError -> _uiState.value = UiState.Failure("Network error!")
            }
        }
    }

    fun addToCollection(collectionId: Int, pictureId: Int) {
        viewModelScope.launch {
            when (val it = feedRepository.addPicToCollection(collectionId, pictureId)) {
                is DataResult.GenericError -> _uiStateAdd.value = UiState.Failure(it.error)
                is DataResult.Success -> _uiStateAdd.value = UiState.Success(Unit)
                is DataResult.NetworkError -> _uiStateAdd.value = UiState.Failure("Network error!")
            }
        }
    }

    fun createCollection(name: String) {
        viewModelScope.launch {
            when (val it = feedRepository.addCollection(name)) {
                is DataResult.GenericError -> _uiStateCreate.value = UiState.Failure(it.error)
                is DataResult.Success -> {
                    _uiStateCreate.value = UiState.Success(Unit)
                    fetchCollections()
                }

                is DataResult.NetworkError -> _uiStateCreate.value =
                    UiState.Failure("Network error!")
            }
        }
    }
}