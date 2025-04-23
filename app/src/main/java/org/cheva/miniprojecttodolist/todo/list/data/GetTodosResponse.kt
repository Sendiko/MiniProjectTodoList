package org.cheva.miniprojecttodolist.todo.list.data

import com.google.gson.annotations.SerializedName

data class GetTodosResponse(

	@field:SerializedName("todos")
	val todos: List<TodosItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class TodosItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("isDone")
	val isDone: Boolean,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
