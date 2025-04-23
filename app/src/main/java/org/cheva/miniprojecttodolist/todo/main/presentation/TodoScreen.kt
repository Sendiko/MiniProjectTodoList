package org.cheva.miniprojecttodolist.todo.main.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.core.ui.components.OutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    state: TodoState,
    onEvent: (TodoEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.todo_label))
                },
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                end = 16.dp,
                start = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(
                    label = stringResource(R.string.title_label),
                    value = state.title,
                    onValueChange = {  }
                )
            }
            item {
                OutlinedTextField(
                    label = stringResource(R.string.desc_label),
                    value = state.description,
                    onValueChange = {  }
                )
            }
            item {
                OutlinedTextField(
                    label = stringResource(R.string.category_label),
                    value = state.category?.name?:"",
                    onValueChange = {  }
                )
            }
            item {
                Button(
                    onClick = {  },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.save_todo))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TodoScreenPrev() {
    TodoScreen(
        state = TodoState(),
        onEvent = { },
        onNavigate = {  }
    )
}