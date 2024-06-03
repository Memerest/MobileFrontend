package com.example.memerestcompose.ui.collections.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.memerestcompose.ui.collections.list.CollectionListViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCollectionDialog(
    collectionListViewModel: CollectionListViewModel,
    onDismiss: () -> Unit,
    scope: CoroutineScope,
    state: SheetState
) {
    ModalBottomSheet(onDismissRequest = { onDismiss() }) {
        var text by remember { mutableStateOf(TextFieldValue()) }

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { collectionListViewModel.createCollection(text.text) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Add")
            }
        }
    }
}