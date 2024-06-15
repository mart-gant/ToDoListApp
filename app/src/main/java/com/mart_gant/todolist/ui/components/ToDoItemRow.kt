package com.mart_gant.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mart_gant.todolist.data.model.ToDoItem

@Composable
fun ToDoItemRow(item: ToDoItem, onRemove: () -> Unit, onToggleDone: () -> Unit, onEdit: (String) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var editedText by remember { mutableStateOf(item.task) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.small)
            .padding(8.dp)
    ) {
        Checkbox(
            checked = item.isDone,
            onCheckedChange = { onToggleDone() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        if (isEditing) {
            BasicTextField(
                value = editedText,
                onValueChange = { editedText = it },
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface)
            )
            IconButton(onClick = {
                onEdit(editedText)
                isEditing = false
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
            }
        } else {
            //Text(text = item.task, modifier = Modifier.weight(1f))
            IconButton(onClick = { isEditing = true }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
        }
        IconButton(onClick = onRemove) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove")
        }
    }
}
