package com.example.memerestcompose.ui.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.memerestcompose.ui.collections.add.AddToCollectionDialog
import com.example.memerestcompose.ui.common.ErrorScreen
import com.example.memerestcompose.ui.common.ListImages


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(feedViewModel: FeedViewModel) {
    val list = feedViewModel.memeList.collectAsLazyPagingItems()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheetId by remember { mutableIntStateOf(1) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()


    Box(modifier = Modifier.fillMaxSize()) {
        when (list.loadState.refresh) {
            is LoadState.Error -> ErrorScreen("Error while fetching feed!") { list.refresh() }
            is LoadState.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .width(128.dp)
                    .align(
                        Alignment.Center
                    ),
            )

            is LoadState.NotLoading -> {
                ListImages(list = list, onLikeClick = {
                    feedViewModel.pressLike(it)
                }, onCollectionClick = {
                    showBottomSheet = true
                    showBottomSheetId = it.id
                }, isCollectionButtonVisible = true
                )
                if (showBottomSheet) {
                    AddToCollectionDialog(
                        hiltViewModel(),
                        showBottomSheetId,
                        { showBottomSheet = false },
                        scope,
                        sheetState
                    )
                }
            }
        }
    }
}