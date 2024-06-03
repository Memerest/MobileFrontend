package com.example.memerestcompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.memerestcompose.ui.collections.feed.CollectionFeedScreen
import com.example.memerestcompose.ui.collections.feed.CollectionFeedViewModel
import com.example.memerestcompose.ui.collections.list.CollectionListScreen
import com.example.memerestcompose.ui.collections.list.CollectionListViewModel
import com.example.memerestcompose.ui.common.ErrorScreen
import com.example.memerestcompose.ui.feed.FeedScreen
import com.example.memerestcompose.ui.feed.FeedViewModel
import com.example.memerestcompose.ui.navigation.BottomNavigationBar
import com.example.memerestcompose.ui.navigation.Screen
import com.example.memerestcompose.ui.weekly.WeeklyFeedViewModel
import com.example.memerestcompose.ui.weekly.WeeklyScreen


@Composable
fun AppScaffold() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val collectionListViewModel: CollectionListViewModel = hiltViewModel()
    val feedViewModel: FeedViewModel = hiltViewModel()
    val weeklyFeedViewModel: WeeklyFeedViewModel = hiltViewModel()
    val collectionFeedViewModel: CollectionFeedViewModel = hiltViewModel()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }, scaffoldState = scaffoldState
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.WeeklyFeed.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Feed.route) {
                FeedScreen(feedViewModel)
            }
            composable(
                "collections/{collectionId}", arguments = listOf(navArgument("collectionId") {
                    type = NavType.StringType
                })
            ) {
                val collectionId = remember {
                    it.arguments?.getString("collectionId", "1")
                }
                CollectionFeedScreen(collectionFeedViewModel, collectionId!!.toInt())
            }
            composable(Screen.Collections.route) {
                CollectionListScreen(collectionListViewModel, navController)
            }
            composable(Screen.WeeklyFeed.route) {
                WeeklyScreen(weeklyFeedViewModel)
            }
            composable(Screen.Upload.route) {
                ErrorScreen(errorMessage = "Upload", {})
            }
        }
    }
}