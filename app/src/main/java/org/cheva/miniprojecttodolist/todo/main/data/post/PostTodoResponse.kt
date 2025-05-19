package org.cheva.miniprojecttodolist.todo.main.data.post

import com.google.gson.annotations.SerializedName

data class PostTodoResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
