package com.example.viikko1.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
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


//vaati tämän jostain syystä
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(
    task: Task,
    taskViewModel: TaskViewModel,
    onDismiss: () -> Unit
) {
    var taskTitle by remember { mutableStateOf(task.title) };
    var taskPriority by remember { mutableStateOf(task.priority.toString()) };
    var taskDesc by remember { mutableStateOf(task.description) };



    AlertDialog(
        onDismissRequest = { onDismiss() }, title = { Text(text = "Modify task") },
        confirmButton = {
            Button(
                onClick =
                    {
                        taskViewModel.updateTask(
                            task.copy(
                                title = taskTitle,
                                priority = taskPriority.toInt(),
                                description = taskDesc
                            )
                        )
                        onDismiss();
                    }) {
                Text("Save Changes");
            }
        },
        dismissButton = {
            Row {
                Button(
                    onClick = {
                        taskViewModel.removeTask(task.id);
                        onDismiss();
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) { Text("Delete") }
                Button(onClick = { onDismiss() })
                { Text("Cancel") }
            }
        },
        text = {
            Column {
                OutlinedTextField(
                    value = taskTitle,
                    label = { Text("Title") },
                    onValueChange = { taskTitle = it },
                    modifier = Modifier.fillMaxWidth()
                );

                Spacer(modifier = Modifier.height(10.dp));

                OutlinedTextField(
                    value = taskDesc,
                    label = { Text("Description") },
                    onValueChange = { taskDesc = it });


                Spacer(modifier = Modifier.height(10.dp));

                OutlinedTextField(
                    value = taskPriority,
                    label = { Text("Priority") },
                    onValueChange = { taskPriority = it }
                )
            }
        },

        )
}