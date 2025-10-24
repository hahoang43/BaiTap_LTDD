package com.example.thuchanh2_tuan4.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun Onboard2Screen(onNext: () -> Unit, onBack: () -> Unit) {
    OnboardTemplate(
        imageRes = 0,
        title = "Increase Work Effectiveness",
        desc = "Track important tasks and improve your productivity statistics.",
        onNext = onNext,
        onBack = onBack
    )
}
