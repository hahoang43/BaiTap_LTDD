package com.example.bt_tuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bt_tuan4.ui.theme.BT_Tuan4Theme
import com.example.bt_tuan4.ui.theme.screens.ConfirmScreen
import com.example.bt_tuan4.ui.theme.screens.ForgetPasswordScreen
import com.example.bt_tuan4.ui.theme.screens.ResetPasswordScreen
import com.example.bt_tuan4.ui.theme.screens.VerifyCodeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BT_Tuan4Theme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "forget") {
                    composable("forget") {
                        ForgetPasswordScreen(navController)
                    }
                    composable(
                        route = "verify/{email}",
                        arguments = listOf(navArgument("email") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email") ?: ""
                        VerifyCodeScreen(navController, email)
                    }
                    composable(
                        route = "reset/{email}/{code}",
                        arguments = listOf(
                            navArgument("email") { type = NavType.StringType },
                            navArgument("code") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email") ?: ""
                        val code = backStackEntry.arguments?.getString("code") ?: ""
                        ResetPasswordScreen(navController, email, code)
                    }
                    composable(
                        route = "confirm/{email}/{code}/{password}",
                        arguments = listOf(
                            navArgument("email") { type = NavType.StringType },
                            navArgument("code") { type = NavType.StringType },
                            navArgument("password") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email") ?: ""
                        val code = backStackEntry.arguments?.getString("code") ?: ""
                        val password = backStackEntry.arguments?.getString("password") ?: ""
                        ConfirmScreen(navController, email, code, password)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BT_Tuan4Theme {
        ForgetPasswordScreen(rememberNavController())
    }
}
