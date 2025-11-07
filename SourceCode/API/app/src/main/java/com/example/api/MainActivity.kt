package com.example.api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.api.ui.screens.TaskDetailScreen
import com.example.api.ui.screens.TaskListScreen

@OptIn(ExperimentalMaterial3Api::class) // Cần thiết cho các composable Material 3 như TopAppBar
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Thay bằng tên theme của bạn (Ví dụ: UTHSmartTasksTheme)
            MaterialTheme {
                val navController = rememberNavController()

                // Khai báo các Route
                NavHost(
                    navController = navController,
                    startDestination = "task_list" // Bắt đầu bằng màn hình danh sách
                ) {

                    // 1. Task List Screen (task_list)
                    composable("task_list") {
                        TaskListScreen(onTaskClick = { taskId ->
                            // Điều hướng đến màn hình chi tiết với tham số taskId
                            navController.navigate("task_detail/$taskId")
                        })
                    }

                    // 2. Task Detail Screen (task_detail/{taskId})
                    composable(
                        route = "task_detail/{taskId}", // Định nghĩa tham số trong route
                        arguments = listOf(navArgument("taskId") {
                            type = NavType.IntType
                        })
                    ) { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getInt("taskId")

                        if (taskId != null) {
                            TaskDetailScreen(
                                taskId = taskId,
                                onBack = {
                                    // Quay lại màn hình list
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}