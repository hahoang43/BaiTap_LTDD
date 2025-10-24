package com.example.quanlythuvien.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun BottomNavBar(navController: NavHostController, currentRoute: String?) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = currentRoute == "library",
            onClick = {
                navController.navigate("library") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            label = { Text("Quản lý") },
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.White
            )
        )
        NavigationBarItem(
            selected = currentRoute == "books",
            onClick = {
                navController.navigate("books") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            label = { Text("DS Sách") },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.White
            )
        )
        NavigationBarItem(
            selected = currentRoute == "students",
            onClick = {
                navController.navigate("students") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            label = { Text("Sinh viên") },
            icon = { Icon(Icons.Filled.Person, contentDescription = null) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.White
            )
        )
    }
}
