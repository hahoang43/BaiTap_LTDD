package com.example.animation.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AnimateSingleValueScreen() {
    var darkTheme by remember { mutableStateOf(false) }

    val background by animateColorAsState(
        targetValue = if (darkTheme) Color.Black else Color.White,
        label = "background"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .clickable { darkTheme = !darkTheme },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (darkTheme) "Dark Mode" else "Light Mode",
            color = if (darkTheme) Color.White else Color.Black
        )
    }
}
