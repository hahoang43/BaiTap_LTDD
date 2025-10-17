package com.example.bt_tuan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bt_tuan3.ui.giaodien.ColumnLayoutScreen
import com.example.bt_tuan3.ui.giaodien.ComponentDetailScreen
import com.example.bt_tuan3.ui.giaodien.ComponentsListScreen
import com.example.bt_tuan3.ui.giaodien.ImageScreen
import com.example.bt_tuan3.ui.giaodien.RowLayoutScreen
import com.example.bt_tuan3.ui.giaodien.TextFieldScreen
import com.example.bt_tuan3.ui.giaodien.WelcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcom"
    ) {
        composable("welcom") {
            WelcomeScreen(navController)
        }

        composable("components_list") {
            ComponentsListScreen(navController)
        }

        composable("text") {
            ComponentDetailScreen(navController = navController, type = "Text")
        }


        composable("image") {
            ImageScreen(navController = navController)
        }


        composable("textfield") {
            TextFieldScreen(navController = navController)
        }


        composable("rowlayout") {
            RowLayoutScreen(navController = navController)
        }

        composable("columnlayout") {
            ColumnLayoutScreen(navController = navController)
        }
    }
}
