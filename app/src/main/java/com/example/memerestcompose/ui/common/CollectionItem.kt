package com.example.memerestcompose.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.memerestcompose.ui.models.CollectionUIModel
import com.example.memerestcompose.ui.theme.MemerestComposeTheme

@Composable
fun CollectionItem(
    item: CollectionUIModel,
    onClick: (CollectionUIModel) -> Unit = {},
    isImageVisible: Boolean = true
) {
    OutlinedCard(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick(item) }
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row {
            if (isImageVisible) {
                MemeImage(url = item.previewUrl)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = item.name,
                Modifier
                    .weight(1f)
                    .padding(10.dp),
                style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = 2.sp),
                fontSize = 30.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionItemPreview() {
    MemerestComposeTheme {
        CollectionItem(CollectionUIModel(1, "Big boobs", ""))
    }
}