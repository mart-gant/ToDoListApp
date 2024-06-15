package com.mart_gant.todolist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.mart_gant.todolist.data.model.ToDoItem

@Composable
fun ToDoList(toDoItems: Any, onRemove: (ToDoItem) -> Unit, onToggleDone: (ToDoItem) -> Unit, onEdit: (ToDoItem, String) -> Unit) {
    Column {
        toDoItems.forEach { item ->
            ToDoItemRow(item = item, onRemove = { onRemove(item) }, onToggleDone = { onToggleDone(item) }, onEdit = { newTask -> onEdit(item, newTask) })
        }
    }
}
