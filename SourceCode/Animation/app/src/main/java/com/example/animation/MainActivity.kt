package com.example.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animation.ui.theme.AnimateContentSizeScreen
import com.example.animation.ui.theme.AnimateSingleValueScreen
import com.example.animation.ui.theme.AnimatedVisibilityScreen
import com.example.animation.ui.theme.CrossfadeScreen
import com.example.animation.ui.theme.InfiniteTransitionScreen
import com.example.animation.ui.theme.MenuScreen
import com.example.animation.ui.theme.TransitionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                AnimationNavGraph(navController)
            }
        }
    }
}

@Composable
fun AnimationNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        composable("menu") { MenuScreen(navController) }
        composable("singleValue") { AnimateSingleValueScreen() }
        composable("visibility") { AnimatedVisibilityScreen() }
        composable("transition") { TransitionScreen() }
        composable("contentSize") { AnimateContentSizeScreen() }
        composable("crossfade") { CrossfadeScreen() }
        composable("infinite") { InfiniteTransitionScreen() }
    }
}
