package com.example.thuchanh2_tuan4.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboard1 : Screen("onboard1")
    object Onboard2 : Screen("onboard2")
    object Onboard3 : Screen("onboard3")
    object Home : Screen("home")
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onNext = { navController.navigate(Screen.Onboard1.route) })
        }
        composable(Screen.Onboard1.route) {
            Onboard1Screen(onNext = { navController.navigate(Screen.Onboard2.route) })
        }
        composable(Screen.Onboard2.route) {
            Onboard2Screen(
                onNext = { navController.navigate(Screen.Onboard3.route) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Onboard3.route) {
            Onboard3Screen(
                onFinish = { 

                    navController.navigate(Screen.Splash.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                 },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Home.route) {
            // You need to create this screen
            // HomeScreen()
        }
    }
}
