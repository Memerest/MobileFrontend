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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.collections.add.AddToCollectionDialog
import com.example.memerestcompose.ui.common.ErrorScreen
import com.example.memerestcompose.ui.common.MemeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun WeeklyScreen(feedViewModel: WeeklyFeedViewModel) {
    val loginState by feedViewModel.uiState.observeAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheetId by remember { mutableIntStateOf(1) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Best posts",
            style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 2.sp),
            fontSize = 30.sp
        )
        when (val tmp = loginState) {
            is UiState.Failure -> ErrorScreen(tmp.e) { feedViewModel.fetchWeeklyFeed() }
            is UiState.Idle -> {
                feedViewModel.fetchWeeklyFeed()
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

            is UiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(tmp.data) {
                        MemeItem(
                            item = it,
                            onLikeClick = { feedViewModel.pressLike(it) },
                            onCollectionClick = { showBottomSheet = true},
                            isCollectionButtonVisible = true,
                        )
                    }
                }
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

            null -> {}
        }
    }
}