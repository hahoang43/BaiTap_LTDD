package com.example.thuchanh2_tuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.thuchanh2_tuan4.ui.screens.AppNavHost
import com.example.thuchanh2_tuan4.ui.screens.Onboard1Screen
import com.example.thuchanh2_tuan4.ui.theme.Thuchanh2_Tuan4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Thuchanh2_Tuan4Theme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}

// Replaced the old preview with a more specific and stable one.
@Preview(showBackground = true)
@Composable
fun Onboard1ScreenPreview() {
    Thuchanh2_Tuan4Theme {
        Onboard1Screen(onNext = {})
    }
}
