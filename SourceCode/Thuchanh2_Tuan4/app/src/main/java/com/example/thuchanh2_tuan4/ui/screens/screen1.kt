package com.example.thuchanh2_tuan4.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun Onboard1Screen(onNext: () -> Unit) {
    OnboardTemplate(
        imageRes = 0,
        title = "Easy Time Management",
        desc = "Manage daily tasks conveniently with priority-based scheduling.",
        onNext = onNext
    )
}
