package com.example.api.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.api.ui.screens.TaskDetailScreen
import com.example.api.ui.screens.TaskListScreen

// Định nghĩa các Route (Đường dẫn)
object Destinations {
    const val TASK_LIST_ROUTE = "task_list"
    const val TASK_DETAIL_ROUTE = "task_detail/{taskId}" // Route với tham số

    fun createDetailRoute(taskId: Int) = "task_detail/$taskId"
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Destinations.TASK_LIST_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // --- 1. Task List Screen ---
        composable(Destinations.TASK_LIST_ROUTE) {
            TaskListScreen(
                onTaskClick = { taskId ->
                    // Khi nhấn vào Task, điều hướng đến màn hình chi tiết
                    navController.navigate(Destinations.createDetailRoute(taskId))
                }
            )
        }

        // --- 2. Task Detail Screen ---
        composable(
            route = Destinations.TASK_DETAIL_ROUTE,
            arguments = listOf(navArgument("taskId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            // Lấy taskId từ tham số đường dẫn
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable

            TaskDetailScreen(
                taskId = taskId,
                onBack = {
                    // Khi nhấn nút Back hoặc Xóa Task, quay lại màn hình List
                    navController.popBackStack()
                }
            )
        }
    }
}