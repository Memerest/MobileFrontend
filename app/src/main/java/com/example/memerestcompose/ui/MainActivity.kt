package com.example.memerestcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memerestcompose.ui.login.LoginScreen
import com.example.memerestcompose.ui.login.RegisterScreen
import com.example.memerestcompose.ui.login.UserViewModel
import com.example.memerestcompose.ui.login.navigation.LoginNavigationScreen
import com.example.memerestcompose.ui.theme.MemerestComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemerestComposeApp()
        }
    }
}

@Composable
fun MemerestComposeApp() {
    MemerestComposeTheme {
        val navController = rememberNavController()
        val userViewModel: UserViewModel = hiltViewModel()
        NavHost(
            navController = navController,
            startDestination = LoginNavigationScreen.LoginScreen.route
        ) {
            composable(route = LoginNavigationScreen.LoginScreen.route, content = {
                LoginScreen(
                    navController = navController, userViewModel
                )
            })
            composable(route = LoginNavigationScreen.RegisterScreen.route,
                content = { RegisterScreen(navController = navController, userViewModel) })
            composable(route = LoginNavigationScreen.AppScaffold.route, content = {
                AppScaffold()
            })

        }
    }
}