package com.example.memerestcompose.ui.collections.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.common.ErrorScreen
import com.example.memerestcompose.ui.common.MemeItem

@Composable
fun CollectionFeedScreen(
    collectionFeedViewModel: CollectionFeedViewModel, collectionId: Int
) {
    val loginState by collectionFeedViewModel.uiStateFeed.observeAsState()
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Collection feed",
            style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 2.sp),
            fontSize = 30.sp
        )
        when (val tmp = loginState) {
            is UiState.Failure -> ErrorScreen(tmp.e) {
                collectionFeedViewModel.fetchCollectionFeed(
                    collectionId
                )
            }

            is UiState.Idle -> {
                collectionFeedViewModel.fetchCollectionFeed(collectionId)
            }

            is UiState.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(256.dp)
                        .align(
                            Alignment.Center
                        ),
                )
            }

            is UiState.Success -> LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tmp.data) {
                    MemeItem(
                        item = it,
                        onLikeClick = {},
                        onCollectionClick = {},
                        isCollectionButtonVisible = false,
                        isLikeButtonVisible = false
                    )
                }
            }

            null -> {}
        }
    }
}


