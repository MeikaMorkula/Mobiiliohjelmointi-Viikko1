package com.example.viikko1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.addTask
import com.example.viikko1.domain.mockTasks
import com.example.viikko1.domain.sortTasksByDone
import com.example.viikko1.domain.sortTasksByDueDate
import com.example.viikko1.domain.sortTasksByPriority
import com.example.viikko1.domain.toggleDone
import com.example.viikko1.ui.theme.Viikko1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko1Theme {
                HomeScreen()
            }
        }
    }
}


@Composable
fun TaskNameField(
    title: String,
    onTitleChange: (String)-> Unit
)
{
    OutlinedTextField(
        value=title,
        onValueChange = onTitleChange,
        label= {Text("Task name")}

    )
}

@Composable
fun HomeScreen()
{
    var tasktitle by remember { mutableStateOf("")}
    var taskList by remember { mutableStateOf(mockTasks) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row()
        {
            TaskNameField(
                title =tasktitle,
                onTitleChange = {tasktitle = it}
            )
            Button(
                onClick = {
                    val newTask = Task(
                        id = taskList.size + 1,
                        title = tasktitle,
                        description = "Description ${taskList.size + 1}",
                        priority = 2,
                        dueDate = "2-2-2026",
                        done = false,
                    )
                    taskList = addTask(taskList, newTask);
                },
                content= {
                    Text("Add new task");
                }

            )
        }

        LazyColumn(modifier = Modifier.weight(1f)
            .fillMaxWidth()) {
            items(taskList){task ->
                Row(modifier = Modifier.fillMaxWidth())
                {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("${task.id}")
                        Text(" Title: ${task.title}" )
                        Text("Description: ${task.description}")
                        Text("Priority: ${task.priority}")
                        Text("Due date: ${task.dueDate}")
                        Text("Done: ${task.done}")
                    }

                    Button(onClick =
                        {
                            taskList = toggleDone(taskList, task.id)
                        },
                        content = {
                            Text(if(task.done)"Undo" else "Done")
                        }
                        )
                }

            }
        }

        Row() {

            Button(
                onClick = {
                    taskList = sortTasksByPriority(taskList);
                },
                content= {
                    Text("Sort by priority");
                }

            )
            Button(
                onClick = {
                    taskList = sortTasksByDueDate(taskList);
                },
                content= {
                    Text("Sort by date");
                }
            )
            Button(
                onClick = {
                    taskList = sortTasksByDone(taskList);
                },
                content= {
                    Text("Sort by Done");
                }

            )
        }
    }
}

