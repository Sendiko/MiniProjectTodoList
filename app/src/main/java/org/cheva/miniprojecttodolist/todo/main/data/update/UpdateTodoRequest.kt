package org.cheva.miniprojecttodolist.todo.main.data.update

import com.google.gson.annotations.SerializedName

data class UpdateTodoRequest(

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("category")
    val category: String
)
