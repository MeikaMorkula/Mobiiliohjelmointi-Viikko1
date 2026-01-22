package com.example.viikko1.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {


    var tasks by mutableStateOf(listOf<Task>())
        private set
    init {
        tasks = listOf(
            Task(1, title="task 1", "Description 1", priority = 2, dueDate = "2-2-2026", done= false),
            Task(2, title="task 2", "Description 2", priority = 3, dueDate = "2-4-2026", done= true),
            Task(3, title="task 3", "Description 3", priority = 1, dueDate = "1-2-2026", done= false),
            Task(4, title="task 4", "Description 4", priority = 2, dueDate = "2-2-2026", done= false),
            Task(5, title="task 5", "Description 5", priority = 1, dueDate = "3-2-2026", done= false),
            Task(6, title="task 6", "Description 6", priority = 2, dueDate = "4-2-2026", done= true),
            Task(7, title="task 7", "Description 7", priority = 3, dueDate = "2-2-2026", done= false),
        )
    }

    fun addTask(Name: String)
    {

        val newTask = Task(
            id = tasks.size + 1,
            title = Name,
            description = "Description ${tasks.size + 1}",
            priority = 2,
            dueDate = "2-2-2026",
            done = false,
        )

       tasks = tasks+newTask;
    }

    fun removeTask(Id: Int)
    {
        tasks = tasks.filterNot { it.id == Id};
    }

    fun sortTasksByDueDate()
    {
        tasks = tasks.sortedBy {it.dueDate};
    }

    fun sortTasksByDone(done: Boolean)
    {
        tasks =tasks.sortedBy {it.done};
    }

    fun toggleDone(Id:Int){
        tasks = tasks.map{if (it.id == Id) it.copy(done = !it.done) else it}
    }
}