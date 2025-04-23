package org.cheva.miniprojecttodolist.todo.main.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.todo.list.data.TodosItem

@Composable
fun TodoItem(
    todo: TodosItem,
    onCheckedChange: (Boolean) -> Unit,
    onTodoClick: () -> Unit,
) {
    Surface(
        onClick = onTodoClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
                Text(text = todo.description, style = MaterialTheme.typography.bodyLarge)
            }
            Checkbox(checked = todo.isDone, onCheckedChange = onCheckedChange)
        }
    }
}