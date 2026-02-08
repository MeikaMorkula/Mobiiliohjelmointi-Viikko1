package com.example.viikko1.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.TaskViewModel
import java.time.LocalDate


//vaati tämän jostain syystä
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDialog(
    task: Task?,
    taskViewModel: TaskViewModel,
    onDismiss: () -> Unit
) {

    //Jos task on null niin silloin tehdään uusi taski, muuten muokataan olemassa olevaa
    val isEditMode = task != null

    val tasks by taskViewModel.tasks.collectAsState()
    var taskTitle by remember { mutableStateOf(task?.title ?: "") };
    var taskPriority by remember { mutableStateOf(task?.priority.toString() ?: "2") };
    var taskDesc by remember { mutableStateOf(task?.description ?: "") };
    var taskDueDate by remember { mutableStateOf(task?.dueDate ?: LocalDate.now().toString()) }



    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(if (isEditMode) "Edit Task" else "Add Task") },
        confirmButton = {
            Button(
                onClick =

                    {
                        if (isEditMode) {
                            taskViewModel.updateTask(
                                task.copy(
                                    title = taskTitle,
                                    priority = taskPriority.toInt(),
                                    description = taskDesc ,
                                    dueDate = taskDueDate
                                )
                            )
                        } else {
                            taskViewModel.addTask(
                                Task(
                                    id = tasks.size +1,
                                    title = taskTitle,
                                    description = taskDesc,
                                    dueDate = taskDueDate,
                                    priority = taskPriority.toIntOrNull() ?: 2,
                                    done = false
                                )
                            )
                        }

                        onDismiss();
                    }) {
                Text("Save Changes");
            }
        },
        dismissButton = {
            Row {
                if (isEditMode) {
                    Button(
                        onClick = {
                            taskViewModel.removeTask(task.id);
                            onDismiss();
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) { Text("Delete") }
                }

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
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = taskDueDate,
                    label = { Text("Due Date") },
                    onValueChange = { taskDueDate = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },

        )
}