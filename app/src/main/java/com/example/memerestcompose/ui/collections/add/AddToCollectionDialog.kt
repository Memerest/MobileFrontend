package com.example.memerestcompose.ui.collections.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.collections.list.CollectionListViewModel
import com.example.memerestcompose.ui.common.CollectionItem
import com.example.memerestcompose.ui.common.ErrorScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
@ExperimentalMaterial3Api
fun AddToCollectionDialog(
    collectionListViewModel: CollectionListViewModel,
    pictureId: Int,
    onDismiss: () -> Unit,
    scope: CoroutineScope,
    state: SheetState
) {
    val collectionListState by collectionListViewModel.uiState.observeAsState()
    ModalBottomSheet(onDismissRequest = { onDismiss() }) {
        when (val tmp = collectionListState) {
            is UiState.Failure -> ErrorScreen(tmp.e) { collectionListViewModel.fetchCollections() }
            is UiState.Idle -> {
                collectionListViewModel.fetchCollections()
            }

            is UiState.Loading -> Box(modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(128.dp)
                        .align(
                            Alignment.Center
                        ),
                )
            }

            is UiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(tmp.data) { collectionUIModel ->
                        CollectionItem(item = collectionUIModel, onClick = {
                            collectionListViewModel.addToCollection(
                                collectionUIModel.collectionId, pictureId
                            )
                        }, isImageVisible = false)
                    }
                }
            }

            null -> {}
        }

    }
}