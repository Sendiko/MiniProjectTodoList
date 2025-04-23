package org.cheva.miniprojecttodolist.todo.list.data

import org.cheva.miniprojecttodolist.todo.core.data.Todo
import org.cheva.miniprojecttodolist.todo.list.presentation.component.Category

val todos = listOf(
    Todo(title = "asbc", description = "lksdjflsdlk;ajdfklsdjfskl;dfjklsdjfskl;dfjaskl;dfj;ljfaj", isCompleted = true, category = Category.EDUCATION),
    Todo(title = "asbc", description = "lksdjflsd", isCompleted = true, category = Category.WORK),
    Todo(title = "asbc", description = "lksdjflsd", isCompleted = false, category = Category.HOBBY),
    Todo(title = "asbc", description = "lksdjflsd", isCompleted = false, category = Category.COMPETITION),
    Todo(title = "asbc", description = "lksdjflsd", isCompleted = true, category = Category.GOALS),
)