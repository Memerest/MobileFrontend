package com.example.memerestcompose.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(
    errorMessage: String, onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Oops!", fontSize = 24.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessage, fontSize = 16.sp, color = MaterialTheme.colors.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Try Again")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorScreen() {
    ErrorScreen(errorMessage = "Something went wrong. Please try again.",
        onRetry = { /* Add retry logic here */ })
}
