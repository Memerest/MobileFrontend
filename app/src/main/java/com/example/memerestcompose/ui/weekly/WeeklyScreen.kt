package com.example.memerestcompose.ui.weekly

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
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

@Composable()
fun WeeklyScreen(feedViewModel: WeeklyFeedViewModel) {
    val loginState by feedViewModel.uiState.observeAsState()
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Best posts",
            style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 2.sp),
            fontSize = 30.sp
        )
        when (val tmp = loginState) {
            is UiState.Failure -> ErrorScreen(tmp.e) { feedViewModel.fetchCollections() }
            is UiState.Idle -> {  feedViewModel.fetchCollections()}
            is UiState.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(256.dp)
                        .align(
                            Alignment.Center
                        ),
                )
            }
            is UiState.Success -> LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(tmp.data) {
                    MemeItem(
                        item = it,
                        isCollectionButtonVisible = true,
                    )
                }
            }
            null -> {}
        }
    }
}