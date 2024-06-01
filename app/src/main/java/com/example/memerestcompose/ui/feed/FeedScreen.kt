package com.example.memerestcompose.ui.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.memerestcompose.ui.common.ErrorScreen
import com.example.memerestcompose.ui.common.ListImages


@Composable
fun FeedScreen(feedViewModel: FeedViewModel) {
    val list = feedViewModel.memeList.collectAsLazyPagingItems()
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
            is LoadState.NotLoading -> ListImages(
                list = list,
                onLikeClick = {
                    feedViewModel.pressLike(it)
                },
                onCollectionClick = {

                },
            )}
    }
}