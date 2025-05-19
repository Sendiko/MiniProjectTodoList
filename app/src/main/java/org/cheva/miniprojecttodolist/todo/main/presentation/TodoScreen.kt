package org.cheva.miniprojecttodolist.todo.main.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.core.ui.components.OutlinedTextField
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    state: TodoState,
    onEvent: (TodoEvent) -> Unit,
    onNavigate: (Any??) -> Unit
) {

    LaunchedEffect(state.successPost) {
        if (state.successPost) {
            onNavigate(null)
        }
    }

    LaunchedEffect(state.id) {
        Log.i("TODO_ID", "TodoScreen: $state.id")
        if (state.id != null)
            onEvent(TodoEvent.FetchTodo)
    }

    Scaffold(
        modifier = modifier,
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
                    onValueChange = {
                        onEvent(TodoEvent.OnTitleChanged(it))
                    }
                )
            }
            item {
                OutlinedTextField(
                    label = stringResource(R.string.desc_label),
                    value = state.description,
                    onValueChange = {
                        onEvent(TodoEvent.OnDescriptionChanged(it))
                    }
                )
            }
            item {
                ExposedDropdownMenuBox(
                    expanded = state.isSelectingCategory,
                    onExpandedChange = { onEvent(TodoEvent.OnDropdownChanged(it)) },
                ) {
                    OutlinedTextField(
                        modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
                        label = stringResource(R.string.category_label),
                        value = state.category?.name ?: "",
                        readOnly = true,
                        onValueChange = {
                            onEvent(TodoEvent.OnCategoryChanged(Category.valueOf(it)))
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = state.isSelectingCategory)
                        }
                    )
                    ExposedDropdownMenu(
                        expanded = state.isSelectingCategory,
                        onDismissRequest = { TodoEvent.OnDropdownChanged(false) },
                    ) {
                        Category.entries.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option.name) },
                                onClick = {
                                    onEvent(TodoEvent.OnCategoryChanged(option))
                                    onEvent(TodoEvent.OnDropdownChanged(false))
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }

                }
            }
            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(TodoEvent.OnSaveTodoClicked)
                    }
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
        onNavigate = { }
    )
}