package com.mart_gant.todolist.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.mart_gant.todolist.data.db.AppDatabase
import com.mart_gant.todolist.data.model.ToDoItem
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "todo-database"
    ).build()

    private val toDoDao = db.toDoDao()

    var toDoItems = mutableStateListOf<ToDoItem>()
        private set

    init {
        viewModelScope.launch {
            toDoItems.addAll(toDoDao.getAll())
        }
    }

    fun addToDoItem(task: String) {
        viewModelScope.launch {
            val newItem = ToDoItem(task = task, isDone = false)
            toDoDao.insert(newItem)
            toDoItems.add(newItem)
        }
    }

    fun removeToDoItem(item: ToDoItem) {
        viewModelScope.launch {
            toDoDao.delete(item)
            toDoItems.remove(item)
        }
    }

    fun toggleDone(item: ToDoItem) {
        viewModelScope.launch {
            item.isDone = !item.isDone
            toDoDao.update(item)
        }
    }

    fun editToDoItem(item: ToDoItem, newTask: String) {
        viewModelScope.launch {
            item.task = newTask
            toDoDao.update(item)
        }
    }
}
