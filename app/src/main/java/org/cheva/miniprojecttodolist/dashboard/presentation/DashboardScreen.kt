package org.cheva.miniprojecttodolist.dashboard.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.dashboard.data.Todo
import org.cheva.miniprojecttodolist.dashboard.presentation.component.Category
import org.cheva.miniprojecttodolist.dashboard.presentation.component.TodoItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    username: String,
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(stringResource(R.string.greeting, username))
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
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
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
    val todos = listOf(
        Todo(title = "asbc", description = "lksdjflsdlk;ajdfklsdjfskl;dfjklsdjfskl;dfjaskl;dfj;ljfaj", isCompleted = true, category = Category.EDUCATION),
        Todo(title = "asbc", description = "lksdjflsd", isCompleted = true, category = Category.WORK),
        Todo(title = "asbc", description = "lksdjflsd", isCompleted = false, category = Category.HOBBY),
        Todo(title = "asbc", description = "lksdjflsd", isCompleted = false, category = Category.COMPETITION),
        Todo(title = "asbc", description = "lksdjflsd", isCompleted = true, category = Category.GOALS),
    )
    DashboardScreen(
        state = DashboardState(todos = todos, name = "Sendiko"),
        onEvent = {  },
        onNavigate = {  },
        username = "Sendiko"
    )
}