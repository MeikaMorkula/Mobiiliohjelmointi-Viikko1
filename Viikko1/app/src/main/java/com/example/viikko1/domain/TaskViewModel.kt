package com.example.viikko1.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())

    val tasks: StateFlow<List<Task>> = _tasks;


    private var mockTasks: List<Task> = listOf(
        Task(
            1,
            title = "task 1",
            "Description 1",
            priority = 2,
            dueDate = "2026-2-2",
            done = false
        ),
        Task(2, title = "task 2", "Description 2", priority = 3, dueDate = "2-4-2026", done = true),
        Task(
            3,
            title = "task 3",
            "Description 3",
            priority = 1,
            dueDate = "2026-2-1",
            done = false
        ),
        Task(
            4,
            title = "task 4",
            "Description 4",
            priority = 2,
            dueDate = "2026-2-2",
            done = false
        ),
        Task(
            5,
            title = "task 5",
            "Description 5",
            priority = 1,
            dueDate = "2026-2-3",
            done = false
        ),
        Task(6, title = "task 6", "Description 6", priority = 2, dueDate = "2026-2-4", done = true),
        Task(
            7,
            title = "task 7",
            "Description 7",
            priority = 3,
            dueDate = "2026-2-3",
            done = false
        ),
    )

    init {
        _tasks.value = mockTasks;
    }

    fun addTask(newTask: Task) {
        mockTasks = mockTasks + newTask;

        _tasks.value = mockTasks;
    }

    fun removeTask(Id: Int) {
        mockTasks = mockTasks.filterNot { it.id == Id };
        _tasks.value = mockTasks;
    }

    fun sortTasksByDueDate() {
        mockTasks = mockTasks.sortedBy { it.dueDate };
        _tasks.value = mockTasks;
    }

    fun sortTasksByDone(done: Boolean) {
        mockTasks = mockTasks.sortedBy { it.done };
        _tasks.value = mockTasks;
    }

    fun toggleDone(Id: Int) {
        mockTasks = mockTasks.map { if (it.id == Id) it.copy(done = !it.done) else it }
        _tasks.value = mockTasks;
    }

    fun updateTask(modifiedTask: Task) {
        mockTasks = mockTasks.map { if (it.id == modifiedTask.id) modifiedTask else it }
        _tasks.value = mockTasks
    }
}