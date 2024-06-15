package com.mart_gant.todolist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mart_gant.todolist.ui.components.ToDoList
import com.mart_gant.todolist.viewmodel.ToDoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(viewModel: ToDoViewModel = viewModel()) {
    val toDoItems by viewModel.toDoItems.collectAsState()
    var newTask by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TaskInput(
            newTask = newTask,
            onTaskChange = { newTask = it },
            onAddTask = {
                if (newTask.isNotEmpty()) {
                    viewModel.addToDoItem(newTask)
                    newTask = "" // Clear input after adding
                    showError = false // Hide error if task added
                } else {
                    showError = true
                }
            },
            showError = showError
        )
        Spacer(modifier = Modifier.height(16.dp))
        ToDoList(
            toDoItems = toDoItems,
            onRemove = viewModel::removeToDoItem,
            onToggleDone = viewModel::toggleDone,
            onEdit = viewModel::editToDoItem
        )
    }
}

@Composable
private fun TaskInput(
    newTask: String,
    onTaskChange: (String) -> Unit,
    onAddTask: () -> Unit,
    showError: Boolean
) {
    Column {
        OutlinedTextField(
            value = newTask,
            onValueChange = onTaskChange,
            label = { Text("New Task") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
                .padding(8.dp),
            isError = showError
        )
        if (showError) {
            Text(text = "Task cannot be empty", color = Color.Red)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onAddTask) {
            Text("Add")
        }
    }
}
