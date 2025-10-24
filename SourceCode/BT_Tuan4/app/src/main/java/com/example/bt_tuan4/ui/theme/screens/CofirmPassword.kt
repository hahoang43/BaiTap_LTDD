package com.example.bt_tuan4.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ConfirmScreen(navController: NavController, email: String, code: String, password: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Confirm Information", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email, 
            onValueChange = {}, 
            label = { Text("Email") }, 
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = Color.Black,
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            )
        )
        OutlinedTextField(
            value = code, 
            onValueChange = {}, 
            label = { Text("Code") }, 
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = Color.Black,
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            )
        )
        OutlinedTextField(
            value = password, 
            onValueChange = {}, 
            label = { Text("Password") }, 
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = Color.Black,
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.getBackStackEntry("forget").savedStateHandle["info"] =
                    "Email: $email\nCode: $code\nPassword: $password"
                navController.popBackStack("forget", inclusive = false)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text("Submit")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ConfirmScreenPreview() {
    ConfirmScreen(navController = rememberNavController(), email = "test@example.com", code = "1234", password = "password")
}
