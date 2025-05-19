package org.cheva.miniprojecttodolist.todo.main.data.post

import com.google.gson.annotations.SerializedName

data class PostTodoRequest(

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("isDone")
	val isDone: Boolean
)
