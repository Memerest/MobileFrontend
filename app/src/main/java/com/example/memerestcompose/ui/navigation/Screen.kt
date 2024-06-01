package com.example.memerestcompose.ui.navigation

import androidx.annotation.DrawableRes
import com.example.memerestcompose.R


sealed class Screen(val route: String, val title: String, @DrawableRes val icon: Int) {
    object Feed: Screen("feed", "Feed", R.drawable.ic_feed)
    object Collections: Screen("collections", "Collections", R.drawable.ic_collections)
    object WeeklyFeed: Screen("weekly_feed", "Weekly", R.drawable.ic_weekly)
    object Upload: Screen("upload", "Upload", R.drawable.ic_upload)
}
