package com.example.memerestcompose.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.memerestcompose.ui.models.MemeListUiModel
import com.example.memerestcompose.ui.theme.MemerestComposeTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun ListImages(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<MemeListUiModel>,
    onLikeClick: (MemeListUiModel) -> Unit = {},
    onCollectionClick: (MemeListUiModel) -> Unit = {},
    isCollectionButtonVisible: Boolean = false,
    isLikeButtonVisible: Boolean = true
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier, contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = list.itemCount) { index ->
            val item = list[index]!!
            MemeItem(
                modifier = modifier,
                item = item,
                onLikeClick = onLikeClick,
                onCollectionClick = onCollectionClick,
                isCollectionButtonVisible = isCollectionButtonVisible
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListImagesPreview() {
    MemerestComposeTheme {
        val list = listOf(
            MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ), MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ), MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ), MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ), MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ), MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ), MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ), MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            ), MemeListUiModel(1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                false
            )
        )
        ListImages(
            list = flowOf(PagingData.from(list)).collectAsLazyPagingItems()
        )
    }
}
