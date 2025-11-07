package com.example.animation.ui.theme

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
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
fun TransitionScreen() {
    var loading by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = loading, label = "transition")

    val color by transition.animateColor(label = "color") {
        if (it) Color(0xFF2196F3) else Color(0xFF9C27B0)
    }
    val alpha by transition.animateFloat(label = "alpha") {
        if (it) 1f else 0.4f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color.copy(alpha = alpha))
            .clickable { loading = !loading },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (loading) "Loading..." else "Hi",
            color = Color.White
        )
    }
}
