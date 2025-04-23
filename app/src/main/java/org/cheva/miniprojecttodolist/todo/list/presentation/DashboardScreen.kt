package org.cheva.miniprojecttodolist.todo.list.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.core.navigation.TodoScreen
import org.cheva.miniprojecttodolist.todo.main.presentation.TodoItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(state.todos) {
        if (state.todos.isEmpty()) {
            onEvent(DashboardEvent.OnLoadTodo)
        }
    }

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(stringResource(R.string.greeting, state.name))
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigate(TodoScreen) }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            contentPadding = paddingValues
        ) {
            items(state.todos) {
                TodoItem(
                    todo = it,
                    onCheckedChange = { onEvent(DashboardEvent.OnTodoChecked(it)) },
                    onTodoClick = { onNavigate("") }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DashboardScreenPrev() {
    DashboardScreen(
        state = DashboardState(todos = emptyList(), name = "Sendiko"),
        onEvent = {  },
        onNavigate = {  },
    )
}