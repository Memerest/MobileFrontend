package com.example.memerestcompose.ui.login.navigation

sealed class LoginNavigationScreen(val route: String) {
    object LoginScreen : LoginNavigationScreen("login_screen")
    object RegisterScreen : LoginNavigationScreen("register_screen")
    object BottomBar : LoginNavigationScreen("bottom_bar")
    object AppScaffold : LoginNavigationScreen("app_scaffold")
}