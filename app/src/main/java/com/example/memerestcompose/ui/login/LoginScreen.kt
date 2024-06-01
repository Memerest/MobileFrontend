package com.example.memerestcompose.ui.login

import androidx.compose.runtime.Composable
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.memerestcompose.R
import com.example.memerestcompose.ui.UiState
import com.example.memerestcompose.ui.common.ErrorScreen
import com.example.memerestcompose.ui.login.navigation.LoginNavigationScreen

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {

    val image = painterResource(id = R.drawable.ic_feed)

    val loginState by userViewModel.uiState.observeAsState()
    val passwordVector = painterResource(id = R.drawable.ic_password_eye)
    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }



    when (val tmp = loginState) {
        is UiState.Failure -> ErrorScreen(tmp.e) { userViewModel.refresh() }
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
            navController.popBackStack()
            navController.navigate(LoginNavigationScreen.AppScaffold.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }

        is UiState.Idle -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.TopCenter
                ) {

                    Image(
                        painter = image, contentDescription = "Login main image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth(0.8f)

                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.60f)
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Sign In",
                        style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 2.sp),
                        fontSize = 30.sp
                    )

                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = emailValue,
                            onValueChange = { emailValue = it },
                            label = { Text(text = "Email") },
                            placeholder = { Text(text = "Enter the email") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )

                        OutlinedTextField(
                            value = passwordValue,
                            onValueChange = { passwordValue = it },
                            trailingIcon = {
                                IconButton(onClick = {
                                    passwordVisibility.value = !passwordVisibility.value
                                }) {
                                    Icon(
                                        painter = passwordVector,
                                        contentDescription = "Password icon",
                                        tint = if (passwordVisibility.value) MaterialTheme.colorScheme.onPrimary else Color.Gray
                                    )
                                }
                            },
                            label = { Text(text = "Password") },
                            placeholder = { Text(text = "Enter the password") },
                            singleLine = true,
                            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                        //LoginBaseItem(username, password)
                        Spacer(modifier = Modifier.padding(10.dp))
                        Button(
                            onClick = {
                                userViewModel.login(emailValue, passwordValue)
                            }, modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(50.dp)
                        ) {
                            Text(text = "Sign In", fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                        Text(
                            text = "Create an account",
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier.clickable(onClick = {
                                navController.navigate(LoginNavigationScreen.RegisterScreen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }

                            })
                        )
                        Spacer(modifier = Modifier.padding(20.dp))
                    }
                }
            }
        }

        null -> {}
    }
}
