package com.example.memerestcompose.ui.collections.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.collections.create.CreateCollectionDialog
import com.example.memerestcompose.ui.common.CollectionItem
import com.example.memerestcompose.ui.common.ErrorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionListScreen(
    collectionListViewModel: CollectionListViewModel,
    navController: NavController = rememberNavController()
) {
    val collectionListState by collectionListViewModel.uiState.observeAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheetId by remember { mutableIntStateOf(1) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Your collections",
            style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 2.sp),
            fontSize = 30.sp
        )
        when (val tmp = collectionListState) {
            is UiState.Failure -> ErrorScreen(tmp.e) { collectionListViewModel.fetchCollections() }
            is UiState.Idle -> {
                collectionListViewModel.fetchCollections()
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
                    items(tmp.data) { collectionUIModel ->
                        CollectionItem(item = collectionUIModel, onClick = {
                            navController.navigate("collections/${it.collectionId}")
                        })
                    }
                }
                if (showBottomSheet) {
                    CreateCollectionDialog(
                        collectionListViewModel, { showBottomSheet = false }, scope, sheetState
                    )
                }
                FloatingActionButton(
                    onClick = {
                        showBottomSheet = true
                    },
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }

            null -> {}
        }
    }
}