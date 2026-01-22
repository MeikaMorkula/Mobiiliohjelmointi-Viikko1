package com.example.viikko1.ui.theme

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.TaskViewModel


@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel())
{
    var tasktitle by remember { mutableStateOf("")}
    val taskList = viewModel.tasks;

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(Modifier.height(14.dp))
        TaskNameField(
            title =tasktitle,
            onTitleChange = {tasktitle = it}
        )
        Spacer(Modifier.height(14.dp))
        Row()
        {

            Button(
                onClick = {

                    viewModel.addTask(tasktitle);
                },
                content= {
                    Text("Add new task");
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green
                )

            )
        }

        LazyColumn(modifier = Modifier.weight(1f)
            .fillMaxWidth()) {
            items(taskList){task ->
                ItemRow(
                    onToggleState = {viewModel.toggleDone(task.id)},
                    item=task,
                    onItemdelete = {viewModel.removeTask(task.id)}
                )

            }
        }

        Row() {

            Button(
                onClick = {
                    viewModel.sortTasksByDueDate();
                },
                content= {
                    Text("Sort by date");
                }
            )
            Button(
                onClick = {
                    viewModel.sortTasksByDone(false);
                },
                content= {
                    Text("Sort by Done");
                }

            )
        }
    }
}

@Composable
fun ItemRow(
    item: Task,
    onToggleState: () -> Unit,
    onItemdelete: ()-> Unit
)
{

    Spacer(Modifier.height(10.dp))
    Row(modifier = Modifier.fillMaxWidth())
    {

        Checkbox(
            checked = item.done,
            {onToggleState()}
        )

        Column(modifier = Modifier.weight(1f)) {
            Text("${item.id}")
            Text(" Title: ${item.title}" )
            Text("Priority: ${item.priority}")
            Text("Due date: ${item.dueDate}")
        }

        Button(onClick = onItemdelete,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
        ))
        {
            Text("delete")
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

