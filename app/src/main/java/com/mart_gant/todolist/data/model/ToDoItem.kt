package com.mart_gant.todolist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var task: String,
    var isDone: Boolean
)