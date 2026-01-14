package com.example.viikko1.domain

fun addTask(list: List<Task>, newTask: Task): List<Task>
{
    return list + newTask;
}

fun sortTasksByPriority(list:List<Task>): List<Task>
{
    return list.sortedBy { it.priority };
}

fun sortTasksByDueDate(list:List<Task>): List<Task>
{
    return list.sortedBy {it.dueDate};
}

fun sortTasksByDone(list:List<Task>): List<Task>
{
    return list.sortedBy {it.done};
}

fun toggleDone(list:List<Task>, Id:Int): List<Task>
{
    return list.map { task->
        if(task.id == Id)
        {
            task.copy(done = !task.done);
        }
        else{
            task
        }
    }
}