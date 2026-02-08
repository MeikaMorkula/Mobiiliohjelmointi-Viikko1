package com.example.viikko1.views

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.TaskViewModel

@Composable
fun CalendarScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {

    val taskList by viewModel.tasks.collectAsState()
    var currentTask by remember { mutableStateOf<Task?>(null) }


    val tasksByDate = taskList.groupBy { it.dueDate }


    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(onClick = onNavigateBack) {
                    Text("Return")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Calendar")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {
            tasksByDate.forEach { (date, tasksForDay) ->

                item {
                    Text(
                        text = date,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        fontSize = 22.sp
                    )
                }

                items(tasksForDay) { task ->
                    CalendarItemRow(
                        item = task,
                        onClick = { currentTask = task }
                    )
                }
            }
        }
        currentTask?.let { task ->
            TaskDialog(
                task = task,
                taskViewModel = viewModel,
                onDismiss = { currentTask = null }
            )
        }

    }
}


@Composable
fun CalendarItemRow(item: Task, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = item.title
        )
        Text(
            text = item.dueDate
        )


    }
}

