package com.example.thuchanh2_tuan4.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun Onboard3Screen(onFinish: () -> Unit, onBack: () -> Unit) {
    OnboardTemplate(
        imageRes = 0,
        title = "Reminder Notification",
        desc = "Get automatic reminders for upcoming tasks and deadlines.",
        onNext = onFinish,
        onBack = onBack,
        nextText = "Get Started"
    )
}
