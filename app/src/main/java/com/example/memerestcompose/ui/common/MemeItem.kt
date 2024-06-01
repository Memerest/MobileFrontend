package com.example.memerestcompose.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.memerestcompose.ui.models.MemeListUiModel
import com.example.memerestcompose.ui.theme.MemerestComposeTheme

@Composable
fun MemeItem(
    modifier: Modifier = Modifier,
    item: MemeListUiModel,
    onLikeClick: (MemeListUiModel) -> Unit = {},
    onCollectionClick: (MemeListUiModel) -> Unit = {},
    isCollectionButtonVisible: Boolean = false
) {
    var isLiked by rememberSaveable { mutableStateOf(item.isLiked) }
    var isAdded by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium),
        shape = MaterialTheme.shapes.medium,
    ) {
        MemeImage(
            url = item.previewUrl, modifier = Modifier.fillMaxWidth()
        )
        Row {
            IconToggleButton(checked = isLiked,
                onCheckedChange = {
                    onLikeClick(item)
                    isLiked = !isLiked
                }) {
                if (!isLiked) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Like")
                } else {
                    Icon(Icons.Filled.Favorite, contentDescription = "Like")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (isCollectionButtonVisible) {
                IconToggleButton(
                    checked = isAdded,
                    onCheckedChange = {
                        onCollectionClick(item)
                        isAdded = !isAdded
                    }) {
                    if (!isAdded) {
                        Icon(Icons.Outlined.AddCircle, contentDescription = "Add")
                    } else {
                        Icon(Icons.Filled.Done, contentDescription = "Add")
                    }
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun MemeItemPreview() {
    MemerestComposeTheme {
        MemeItem(
            item = MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ),
            isCollectionButtonVisible = true
        )
    }
}