package com.example.memerestcompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memerestcompose.ui.common.ErrorScreen
import com.example.memerestcompose.ui.feed.FeedScreen
import com.example.memerestcompose.ui.navigation.BottomNavigationBar
import com.example.memerestcompose.ui.navigation.Screen
import com.example.memerestcompose.ui.weekly.WeeklyScreen


@Composable
fun AppScaffold() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }, scaffoldState = scaffoldState
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Feed.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Feed.route) {
                FeedScreen(feedViewModel = hiltViewModel())
            }
            composable(Screen.Collections.route) {
                ErrorScreen(errorMessage = "Collection", {})
            }
            composable(Screen.WeeklyFeed.route) {
                WeeklyScreen(feedViewModel = hiltViewModel())
            }
            composable(Screen.Upload.route) {
                ErrorScreen(errorMessage = "Upload", {})
            }
        }
    }
}