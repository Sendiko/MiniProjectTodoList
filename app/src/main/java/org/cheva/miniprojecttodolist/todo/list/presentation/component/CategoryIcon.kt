package org.cheva.miniprojecttodolist.todo.list.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.COMPETITION
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.EDUCATION
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.GAS
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.GOALS
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.GROCERIES
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.HEALTH
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.HOBBY
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.SPORTS
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.SUBSCRIPTION
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category.WORK

@Composable
fun CategoryIcon(
    modifier: Modifier = Modifier,
    category: Category,
) {
    val icon = when (category) {
        COMPETITION -> R.drawable.competition
        EDUCATION -> R.drawable.education
        GAS -> R.drawable.gas
        GOALS -> R.drawable.goals
        GROCERIES -> R.drawable.groceries
        HEALTH -> R.drawable.health
        HOBBY -> R.drawable.hobby
        SPORTS -> R.drawable.sports
        SUBSCRIPTION -> R.drawable.subscription
        WORK -> R.drawable.work
    }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.tertiary,
        shape = CircleShape
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = category.name,
            modifier = Modifier.padding(4.dp)
                .size(32.dp)
        )
    }
}

@Preview
@Composable
private fun CategoryIconPrev() {
    CategoryIcon(category = SUBSCRIPTION)
}