package com.mart_gant.todolist.data.db

import androidx.room.*
import com.mart_gant.todolist.data.model.ToDoItem

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_items")
    fun getAll(): List<ToDoItem>

    @Insert
    fun insert(toDoItem: ToDoItem)

    @Delete
    fun delete(toDoItem: ToDoItem)

    @Update
    fun update(toDoItem: ToDoItem)
}