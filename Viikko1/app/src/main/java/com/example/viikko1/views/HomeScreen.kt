package com.example.viikko1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.TaskViewModel
import androidx.compose.runtime.collectAsState
import java.time.LocalDate


@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onNavigateToCalendar: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val taskList by viewModel.tasks.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var currentTask by remember { mutableStateOf<Task?>(null) }

    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Tasks",
                modifier = Modifier.weight(1f)
            )
            Button(onClick = onNavigateToSettings)
            {
                Text("Settings")
            }
            Spacer(Modifier.height(14.dp))
            Button(onClick = onNavigateToCalendar) {
                Text("Calendar")
            }
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(Modifier.height(14.dp))

            Row()
            {
                Button(
                    onClick = { showAddDialog = true },
                    content = { Text("Add task") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green
                    )
                )
                Spacer(Modifier.height(14.dp))
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(taskList) { task ->
                    ItemRow(
                        onToggleState = { viewModel.toggleDone(task.id) },
                        item = task,
                        onClick = { currentTask = task }
                    )

                }
            }

            Row() {

                Button(
                    onClick = {
                        viewModel.sortTasksByDueDate();
                    },
                    content = {
                        Text("Sort by date");
                    }
                )
                Button(
                    onClick = {
                        viewModel.sortTasksByDone(false);
                    },
                    content = {
                        Text("Sort by Done");
                    }

                )

                currentTask?.let { task ->
                    TaskDialog(
                        task = task,
                        taskViewModel = viewModel,
                        onDismiss = { currentTask = null }
                    )
                }
            }
        }
    }
    if (showAddDialog) {
        TaskDialog(
            task = null,
            taskViewModel = viewModel,
            onDismiss = { showAddDialog = false }
        )
    }
}

@Composable
fun ItemRow(
    item: Task,
    onToggleState: () -> Unit,
    onClick: () -> Unit
) {

    Spacer(Modifier.height(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE3F2FD))
            .clickable { onClick() })
    {

        Checkbox(
            checked = item.done,
            { onToggleState() }
        )

        Column(modifier = Modifier.weight(1f)) {
            Text("${item.id}")
            Text(" Title: ${item.title}")
            Text("Priority: ${item.priority}")
            Text("Due date: ${item.dueDate}")
        }


    }
}

@Composable
fun TaskNameField(
    title: String,
    onTitleChange: (String) -> Unit
) {
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        label = { Text("Task name") }

    )
}

