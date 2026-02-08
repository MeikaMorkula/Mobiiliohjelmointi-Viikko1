package com.example.viikko1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viikko1.domain.TaskViewModel

import com.example.viikko1.views.HomeScreen
import com.example.viikko1.ui.theme.Viikko1Theme
import com.example.viikko1.views.CalendarScreen
import com.example.viikko1.views.ROUTE_CALENDAR
import com.example.viikko1.views.ROUTE_HOME
import com.example.viikko1.views.ROUTE_SETTINGS
import com.example.viikko1.views.Settings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko1Theme {
                val navController = rememberNavController()
                val viewModel: TaskViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = ROUTE_HOME
                ) {
                    composable(ROUTE_HOME) {
                        HomeScreen(
                            viewModel = viewModel,
                            onNavigateToCalendar = {
                                navController.navigate(ROUTE_CALENDAR)
                            },
                            onNavigateToSettings =
                                {
                                    navController.navigate(ROUTE_SETTINGS)
                                }
                        )
                    }
                    composable(ROUTE_CALENDAR)
                    {
                        CalendarScreen(
                            viewModel = viewModel,
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(ROUTE_SETTINGS) {
                        Settings(
                            viewModel = viewModel,
                            onNavigateBack = {
                                navController.popBackStack()
                            })
                    }
                }

            }
        }
    }
}



