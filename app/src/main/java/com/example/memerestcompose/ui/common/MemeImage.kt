package com.example.memerestcompose.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.memerestcompose.R

@Composable
fun MemeImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = "Meme"
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .build(),
        modifier = modifier,
        contentScale = ContentScale.FillWidth,
        contentDescription = contentDescription,
        placeholder = painterResource(R.drawable.ic_launcher_background)
    )
}
