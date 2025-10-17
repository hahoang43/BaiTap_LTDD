package com.example.bt_tuan3.ui.giaodien


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun TextFieldScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        TextButton(onClick = { navController.popBackStack() }) {
            Text("←", fontSize = 32.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))



        Text(
            text = "TextField",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color(0xFF007BFF),
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )


        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Thông tin nhập") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (text.isNotEmpty()) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
        }
    }
}
