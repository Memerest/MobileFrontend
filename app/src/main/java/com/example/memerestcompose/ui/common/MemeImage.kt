package com.example.memerestcompose.ui.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun MemeImage(
    url: String, modifier: Modifier = Modifier, contentDescription: String? = "Meme"
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current.applicationContext).data(url)
            .crossfade(true).memoryCachePolicy(
                CachePolicy.ENABLED
            ).build(),
        modifier = modifier,
        loading = {
            CircularProgressIndicator()
        },
        contentScale = ContentScale.FillWidth,
        contentDescription = contentDescription,
    )
}
