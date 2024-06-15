package com.mart_gant.todolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mart_gant.todolist.data.model.ToDoItem

@Database(entities = [ToDoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}